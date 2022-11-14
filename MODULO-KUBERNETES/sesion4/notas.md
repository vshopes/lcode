# SESION 4

## Revisión Ejercicios

https://github.com/Lemoncode/bootcamp-devops-lemoncode/tree/master/02-orquestacion
[bootcamp-devops-lemoncode/02-orquestacion/escenarios-practicos at master · Lemoncode/bootcamp-devops-lemoncode](https://github.com/Lemoncode/bootcamp-devops-lemoncode/tree/master/02-orquestacion/escenarios-practicos)


(los ejercicios propuestos son semejantes a certificación)

primer ejercicio
k describe pod nameofthepod ->sacamos el estatus del pod
k get pod namepod -o yaml > fichero.yaml ->sacamos el yaml asociado al pod
k edit pod nameofthepod -->saca un editor

segundo ejercicio

k logs nameofthepod
k get pod -n namespace    --show-labels   (encontramos el app=backend)
k logs -n namespace -l app=backend
k logs -n namespace -l app=backend -f (en tiempo real)

k get pods -n namespace -o wide

k run bb --image busybox --rm -it --restart=Never -- /bin/sh

Se compruba eu los pods funcionan los pods funcionan 

buscamos el servicio

el problema esta en el servicio, generalmente suele ser el selector
primer error, el selector pone back, debería ser backend
segundo error los puertos estan mal

## Configuraciones

### 1. Configuración en docker

debe segruir el tercer principio de 12 factors apps https://12factor.net/
la configuración debe estar estrictamente separada del código
la imagen no debería tener ningún fichero de configuración, si tenemos imagenes tageadas por entorno, algo hacemos mal
distintos entornos distinta config pero mismo código
el entorno debe proveer la configuracion
el código debe poder ser revisado sin acceder a ninguna credencial

si el repositorio es privado se tiende a meter secretos, esto es arriesgado

### 2. Variables de entorno

La forma mas sencilla de configurar un servicio
Para configurar las v de entorno **env**

Ejemplo


<pre>
angel@lemoncode:~/LEMONCODE/MODULO-KUBERNETES/sesion4$ k apply -f pod.yaml 
pod/demo created
angel@lemoncode:~/LEMONCODE/MODULO-KUBERNETES/sesion4$ k exec demo -it -- /bin/sh
/ # env
KUBERNETES_PORT=tcp://10.96.0.1:443
KUBERNETES_SERVICE_PORT=443
HOSTNAME=demo
SHLVL=1
HOME=/root
TERM=xterm
KUBERNETES_PORT_443_TCP_ADDR=10.96.0.1
PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
KUBERNETES_PORT_443_TCP_PORT=443
KUBERNETES_PORT_443_TCP_PROTO=tcp
KUBERNETES_PORT_443_TCP=tcp://10.96.0.1:443
KUBERNETES_SERVICE_PORT_HTTPS=443
DEMO_VAR=Test1
KUBERNETES_SERVICE_HOST=10.96.0.1
PWD=/
/ # 
</pre>
Tenemos DEMO_VAR

Si el env es una password puede variar para pro y demmo. Si tenemos todo en el yaml necesitaremos dos yaml uno para cada entorno. Para esto aparece un mecanismo de yaml el config map

Tendríamos dos CM uno para desarrollo y otro para dev

con k create cm es muy facll crear el configmap que toca, por tanto desplegamos en tiempo real

se puede meter todo el cm de golpe si metemos data: en el config map y quitamos env y poenmos configMapRef: en el pod

### 3. Volumenes
los config maps pueden mandar ficheros tambien y un concepto nuevo, volumen
el volumen en k no es un volmen de dockr

el volumen exixte mientras existe el pod
en un cm JAMAS un pssword
### 4. Secrets

muy parecido al cm 
codifica en base64, no son secretos, ojo

nunca yamls de secretos

keyboots contra volumenes directamente



















