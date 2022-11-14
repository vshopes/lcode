# SESIÓN 5

## Otros integrantes de la fauna

Controlador, objeto que gestiona otros objetos, replicasets por ejemplo.
Tambien permite desplegar contenedores que sean controladores.

### - StatefulSets

Despleagr aplicaciones con estado, ejemplo típico Base de Datos. Con estado, tiene ownership de unos datos.
El replicaset ahce replicas, StatefulSet le da identidad propia no son replicas. Se contruyen y destruyen en orden. Los Pods tienen nombre concreto y predictivo.
Los pods pueden obtener almacenamiento propio y personalizalo.

pone sufijo predictivo

Aporta DNS a los pods. (ejemplo master slave de un servidor de bbdd bbdd)

permite llamar plantilla de volumen **volumeClaimTemplate**

Con el template se asocia un persistente a coda pod, si el pod cae se recrea sobre el mismo volumen persistente

bases de datos y sistemas de mensajería

ejemplo mysql con master slave tenemos que verlo

### - Almacenamineto persistente (pv) 

El alamcenamineto persistente, debería estar almacenado en un almacén situado fuera del cluster, los volumenes están en el cluster(por ejemplo un NAS).
Si está el disco duro de un nodo, si cae el nodo cae el almacenamiento.
El cluster debe tener un driver que permita acceder al alamcen, a estos drivers, se les llamam aprovisionadores. Los aprovisonadores los gestionan los clouds.
Usando los aprovisionadores se crean distintos "tipos de alamacenamiento".
Con el tipo de almacenamiento creamos un volumen persistente.
Los pods deben pedir un volumen persistente al cluster.

-Tipo almacenamiento         **StorageClass**
-Volumen persistente         **PersistentVolume** (pv)
-Petición que emite un pod   **PersistentVolumeClaim** (pvc)

<pre>
angel@lemoncode:~/LEMONCODE/MODULO-KUBERNETES/sesion5$ k get pv
NAME                                       CAPACITY   ACCESS MODES   RECLAIM POLICY   STATUS      CLAIM             STORAGECLASS   REASON   AGE
demopv                                     1Gi        RWO            Retain           Available                                             3m23s
pvc-efd67178-024e-41b9-92c2-09fc56ed42ba   500Mi      RWO            Delete           Bound       default/demopvc   standard                18s
</pre>
Minikube crea volumenes persistentes on the fly, al no poner la storage class, coge por defecto la default
Para aceder a los volumnes hostPath de minikube, logeando en minikube
<pre>
$ minikube ssh
</pre>


K asocia el pvc por el **storageclass**

### - Jobs, Tareas de un solo uso


### - ConJobs, tareas que se repiten

### - DaemonSets 


