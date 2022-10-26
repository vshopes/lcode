# Deployment

deplyment usa un replicaset por debajo para las replicas

~~~
$ k get deployments\
NAME   READY   UP-TO-DATE   AVAILABLE   AGE
api    10/10   10           10          23m 

$ k get rs\
NAME             DESIRED   CURRENT   READY   AGE
api-5669449c97   0         0         0       9m30s
api-5c68796664   0         0         0       24m
api-68c8dc7458   0         0         0       5m35s
api-74bc54fb65   10        10        10      4m15s
api-77587f66d7   0         0         0       8m14s
~~~

deployment se modifica a partir de "template" del yaml debe hacer rollout (linea 11)

~~~
10  template:
11    metadata:
        labels:
          app: api
      spec:
      containers:
      - name: main
        image: lemoncodersbc/go-hello-world
        ports:
        - containerPort: 80
        env:
        - name: Foo
          value: Barrcode1
~~~



salida **yaml** del pod

~~~
kubectl get pod {podname} -o yaml
~~~
(ejemplo _pod.yaml)

hacer roolout de la version anterior

~~~
kubectl rollout undo deployment {deployname}
~~~

Desplegamos servicios "api"

Exponer un servicio al exterior
servicio nodeport expone en todos los NODOS del cluster, todas las llamadas al puerto se enrutarán al servicio, ojo, solo para premise, todos los nodos escucharían
~~~
$ k get svc
NAME         TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)          AGE
api          NodePort    10.110.13.112   <none>        8080:30435/TCP   5m12s
kubernetes   ClusterIP   10.96.0.1       <none>        443/TCP          7d2h
~~~

en minikube 

~~~
minikube ip
192.168.49.2
wget -qO- http://192.168.49.2:30435
Hello, world! You have called me 1 times.
~~~

En cloud LoadBalancer
~~~
$ k get svc
NAME         TYPE           CLUSTER-IP      EXTERNAL-IP   PORT(S)          AGE
api          LoadBalancer   10.110.13.112   <pending>     8080:30435/TCP   11m
kubernetes   ClusterIP      10.96.0.1       <none>        443/TCP          7d2h
~~~

Como lo hace, depende de cada cluster, por ejemplo en AKS crea una IP publica y la enruta

minikube hace 

~~~
$ minikube tunnel
$ k get svc
NAME         TYPE           CLUSTER-IP      EXTERNAL-IP     PORT(S)          AGE
api          LoadBalancer   10.110.13.112   10.110.13.112   8080:30435/TCP   14m
kubernetes   ClusterIP      10.96.0.1       <none>          443/TCP          7d2h
$ wget -qO- http://10.110.13.112:8080
Hello, world! You have called me 1 times.

~~~

# Ingress 

En un recurso (en api group networking.k8s.io/v1)

Enruta uno o varios servicios a partir de un mismo endpoint (una misma ip)

requiere de un controlador de ingress para funcionar, se hace con umn yaml y un apply.
Podemos utilizar cualquier reverse proxy.(nginx, haproxy, traefik, apache traffic server.... )

Por ejemplo, cuando se crea un recurso ingress actualiza nginx y reinicia nginx.

Los servicios suelen ser **ClusterIP**.

Es un balnaceador de L7, solo **http y https**. Por ejemplo no podemos exponer un mysql. Lo tendría que hacer pa través de otro servicio.
Hay una beta para exponer--->.

canonico, el de kuber
https://github.com/kubernetes/ingress-nginx
el de nginx
https://docs.nginx.com/nginx-ingress-controller/


usamos el canonico
https://kubernetes.github.io/ingress-nginx/deploy/




<pre>
$ minikube addons enable ingress
$ k get ns
NAME              STATUS   AGE
curso             Active   6d23h
default           Active   7d3h
**ingress-nginx**     Active   61s
kube-node-lease   Active   7d3h
kube-public       Active   7d3h
kube-system       Active   7d3h
</pre>

aparece el namespace del nginx
hay un pod y un service
~~~ 
$ k get pod -n ingress-nginx
NAME                                        READY   STATUS      RESTARTS   AGE
ingress-nginx-admission-create-5ktrj        0/1     Completed   0          2m43s
ingress-nginx-admission-patch-xjkqp         0/1     Completed   1          2m43s
ingress-nginx-controller-5959f988fd-vktrl   1/1     Running     0          2m43s
~~~ 
Creamos el ingress.yaml. Al ser por reverse porxy solo va por DNS nunca por IP, hay que dar de alta el DNS en hosts.

Cambiamos el service a ClusterIP (astericar y por defecto es es ClusterIP)
~~~
$ k apply -f ingress.yaml 
ingress.networking.k8s.io/api created
$ k get ing
NAME   CLASS   HOSTS             ADDRESS   PORTS   AGE
api    nginx   testangel.local             80      15s
$ wget -qO- http://testangel.local/api
Hello, world! You have called me 3 times.
~~~

hay que poner en hosts testangel.local  contra la ip de minikube (minikube ip)


# Controladores


El controlador es unc codigo que se puede ejecutar como contenedores o no , si no, se ejecuta en el core de kubernetes.

El controlador ejecuta el reconcile loop, compara estado actual del cluster con el estado deseado. Si el estado coincide, toman las medidas necesarias.
K viene con varios controladores de serie. que se ejecutan en el control plane, pero es posible extendero con controladores nuevos, ejecutados en contenedores.

# Anotaciones para Ingress

Configurando los recursos ingres se hace mediante anotaciones
No hay un estándar de anotaciones. Dependen del controlador ingress usado.
Son metadata en el yaml, "annotations"

las de nginx
https://github.com/kubernetes/ingress-nginx/blob/main/docs/user-guide/nginx-configuration/annotations.md


Cookiee affinity, redirige contra el mismo pod, "sticky sessions"























