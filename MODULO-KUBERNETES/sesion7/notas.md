# Sesion 7

## Job 

CronJob, schedule container image y command, restartPolicy: Never 

DaemonSet, crea un pod en cada nodo

## Características para adaptar el **Schedule**

### nodeSelector 
Conjuto de etiquetas para seleccionar el nodo, solo se despliega en los nodos que tieneen esas etiquetas.
Importante esto cuando tenemos nodos linux y windows por ejemplo para que el job acabe en su sitio, sería este label:
**beta.kubernetes.io/os=linux**

<pre>
angel@lemoncode:~$ k describe node
Name:               minikube
Roles:              control-plane
<b>Labels:             beta.kubernetes.io/arch=amd64
                    beta.kubernetes.io/os=linux
                    kubernetes.io/arch=amd64
                    kubernetes.io/hostname=minikube
                    kubernetes.io/os=linux
                    minikube.k8s.io/commit=fe869b5d4da11ba318eb84a3ac00f336411de7ba
                    minikube.k8s.io/name=minikube
                    minikube.k8s.io/primary=true
                    minikube.k8s.io/updated_at=2022_10_18T15_24_32_0700
                    minikube.k8s.io/version=v1.27.1
                    node-role.kubernetes.io/control-plane=
                    node.kubernetes.io/exclude-from-external-load-balancers=</b>
Annotations:        kubeadm.alpha.kubernetes.io/cri-socket: unix:///var/run/cri-dockerd.sock
                    node.alpha.kubernetes.io/ttl: 0
                    volumes.kubernetes.io/controller-managed-attach-detach: true
CreationTimestamp:  Tue, 18 Oct 2022 15:24:29 +0000
Taints:             <none>
Unschedulable:      false
Lease:
  HolderIdentity:  minikube
  AcquireTime:     <unset>
  RenewTime:       Tue, 15 Nov 2022 18:28:59 +0000
Conditions:
  Type             Status  LastHeartbeatTime                 LastTransitionTime                Reason                       Message
  ----             ------  -----------------                 ------------------                ------                       -------
  MemoryPressure   False   Tue, 15 Nov 2022 18:25:40 +0000   Tue, 18 Oct 2022 15:24:25 +0000   KubeletHasSufficientMemory   kubelet has sufficient memory available
  DiskPressure     False   Tue, 15 Nov 2022 18:25:40 +0000   Tue, 18 Oct 2022 15:24:25 +0000   KubeletHasNoDiskPressure     kubelet has no disk pressure
  PIDPressure      False   Tue, 15 Nov 2022 18:25:40 +0000   Tue, 18 Oct 2022 15:24:25 +0000   KubeletHasSufficientPID      kubelet has sufficient PID available
  Ready            True    Tue, 15 Nov 2022 18:25:40 +0000   Tue, 18 Oct 2022 15:24:29 +0000   KubeletReady                 kubelet is posting ready status
Addresses:
  InternalIP:  192.168.49.2
  Hostname:    minikube
Capacity:
  cpu:                4
  ephemeral-storage:  15371208Ki
  hugepages-2Mi:      0
  memory:             7978468Ki
  pods:               110
Allocatable:
  cpu:                4
  ephemeral-storage:  15371208Ki
  hugepages-2Mi:      0
  memory:             7978468Ki
  pods:               110
System Info:
  Machine ID:                 386775ffbc46464ea356d784cd8cd54c
  System UUID:                d72a87f8-415c-499c-bc3c-caa85f5b1989
  Boot ID:                    5aa15c4b-f78c-4ff3-9ccb-53715d234750
  Kernel Version:             5.15.0-52-generic
  OS Image:                   Ubuntu 20.04.5 LTS
  Operating System:           linux
  Architecture:               amd64
  Container Runtime Version:  docker://20.10.18
  Kubelet Version:            v1.25.2
  Kube-Proxy Version:         v1.25.2
PodCIDR:                      10.244.0.0/24
PodCIDRs:                     10.244.0.0/24
Non-terminated Pods:          (11 in total)
  Namespace                   Name                                         CPU Requests  CPU Limits  Memory Requests  Memory Limits  Age
  ---------                   ----                                         ------------  ----------  ---------------  -------------  ---
  ingress-nginx               ingress-nginx-controller-5959f988fd-vktrl    100m (2%)     0 (0%)      90Mi (1%)        0 (0%)         20d
  kube-system                 coredns-565d847f94-tqwc5                     100m (2%)     0 (0%)      70Mi (0%)        170Mi (2%)     28d
  kube-system                 etcd-minikube                                100m (2%)     0 (0%)      100Mi (1%)       0 (0%)         28d
  kube-system                 kube-apiserver-minikube                      250m (6%)     0 (0%)      0 (0%)           0 (0%)         28d
  kube-system                 kube-controller-manager-minikube             200m (5%)     0 (0%)      0 (0%)           0 (0%)         28d
  kube-system                 kube-proxy-w26ng                             0 (0%)        0 (0%)      0 (0%)           0 (0%)         28d
  kube-system                 kube-scheduler-minikube                      100m (2%)     0 (0%)      0 (0%)           0 (0%)         28d
  kube-system                 metrics-server-769cd898cd-lxnwc              100m (2%)     0 (0%)      200Mi (2%)       0 (0%)         21h
  kube-system                 storage-provisioner                          0 (0%)        0 (0%)      0 (0%)           0 (0%)         28d
  kubernetes-dashboard        dashboard-metrics-scraper-b74747df5-69ks8    0 (0%)        0 (0%)      0 (0%)           0 (0%)         21h
  kubernetes-dashboard        kubernetes-dashboard-57bbdc5f89-zt68b        0 (0%)        0 (0%)      0 (0%)           0 (0%)         21h
Allocated resources:
  (Total limits may be over 100 percent, i.e., overcommitted.)
  Resource           Requests    Limits
  --------           --------    ------
  cpu                950m (23%)  0 (0%)
  memory             460Mi (5%)  170Mi (2%)
  ephemeral-storage  0 (0%)      0 (0%)
  hugepages-2Mi      0 (0%)      0 (0%)
Events:              <none>
</pre>


### Manchar un nodo
<pre>
$ k taint node minikube gpu:NoSchedule
</pre>

impide que nuevos pods sean desplegados en este nodo

NoExecute -> desalojaría todos los pods que estuvieran ejecutando

### Afinidad

Grupos de nodos a través de etiquetas, si quieres poner juntos un api y un servicio, si el pai se depliega en un nodo y pides afinidad el servicio se desplegará donde se despliegue el api.<br>
Antiafinidad es evitar que caigan en el mismo pod.<br>

Ejemplo: Deployment, desplegamos 3 app con 3 redis queremos las app no coincidan y que cada app esté con un redis<br>

redis con afinidad de api
api antiafinidad con la propia api

Hay varios niveles de afinidad, pueden complicar mucho el scheduler.


## API 
 kubectl son todo llamadas a la API. Vemos la llamada al api.

<pre>
angel@lemoncode:~$ k get pods -v8
I1115 18:46:27.523226  495307 loader.go:374] Config loaded from file:  /home/angel/.kube/config
I1115 18:46:27.525421  495307 cert_rotation.go:137] Starting client certificate rotation controller
I1115 18:46:27.535855  495307 round_trippers.go:463] GET https://192.168.49.2:8443/api/v1/namespaces/default/pods?limit=500
I1115 18:46:27.535921  495307 round_trippers.go:469] Request Headers:
I1115 18:46:27.535955  495307 round_trippers.go:473]     User-Agent: kubectl/v1.25.3 (linux/amd64) kubernetes/434bfd8
I1115 18:46:27.535980  495307 round_trippers.go:473]     Accept: application/json;as=Table;v=v1;g=meta.k8s.io,application/json;as=Table;v=v1beta1;g=meta.k8s.io,application/json
I1115 18:46:27.553090  495307 round_trippers.go:574] Response Status: 200 OK in 17 milliseconds
I1115 18:46:27.553127  495307 round_trippers.go:577] Response Headers:
I1115 18:46:27.553160  495307 round_trippers.go:580]     Content-Length: 2935
I1115 18:46:27.553180  495307 round_trippers.go:580]     Date: Tue, 15 Nov 2022 18:46:27 GMT
I1115 18:46:27.553194  495307 round_trippers.go:580]     Audit-Id: 74df6bc7-2a9f-4b44-9368-5ad96e029826
I1115 18:46:27.553213  495307 round_trippers.go:580]     Cache-Control: no-cache, private
I1115 18:46:27.553232  495307 round_trippers.go:580]     Content-Type: application/json
I1115 18:46:27.553245  495307 round_trippers.go:580]     X-Kubernetes-Pf-Flowschema-Uid: 266e0c46-139a-4d21-ba7e-c6c6830ced6a
I1115 18:46:27.553266  495307 round_trippers.go:580]     X-Kubernetes-Pf-Prioritylevel-Uid: 7bd3ba2b-3805-4c19-97b5-dba887dfc625
I1115 18:46:27.553736  495307 request.go:1154] Response Body: {"kind":"Table","apiVersion":"meta.k8s.io/v1","metadata":{"resourceVersion":"935098"},"columnDefinitions":[{"name":"Name","type":"string","format":"name","description":"Name must be unique within a namespace. Is required when creating resources, although some resources may allow a client to request the generation of an appropriate name automatically. Name is primarily intended for creation idempotence and configuration definition. Cannot be updated. More info: http://kubernetes.io/docs/user-guide/identifiers#names","priority":0},{"name":"Ready","type":"string","format":"","description":"The aggregate readiness state of this pod for accepting traffic.","priority":0},{"name":"Status","type":"string","format":"","description":"The aggregate status of the containers in this pod.","priority":0},{"name":"Restarts","type":"string","format":"","description":"The number of times the containers in this pod have been restarted and when the last container in this pod has restarted.","priority":0},{"name":"Age","type":"st [truncated 1911 chars]
No resources found in default namespace.
</pre>

Cualquiera puede desarrollar una app para ejecutar los procesos.<br>
Lens hace lo mismo, recomendable

### Cluster Role, 
Permisos de los pod de acceso al api, porque el api esta expuesta a atodos los nodos


### Service Account (sa)
Un service account le puedes asignar a un pod, (tiene que estar definido en el mismo mnamespace)

### RoleBinding
Engancha un ClusterRole con el ServiceAccount


## Serverless

esta de moda 
despliegue agil
escala rápidamente

se pueden ejecutar serverless en kubernetes

exsten tambien implementaciones serverless de kubernetes (EKS Fargare, AKS virtual nodes)

Porque ejecutar serverless en k8s
- facil adopción del cloud hibrido/multicloud
- una sola plataforma que  gestionar
- operacioens unificadas
- mas control sobre el hw
- ejecutar workloads serverless en el mismo entorno que otros worloads ( service mesh, entornos compartidos)

El futuro de k8s es serverles, 
- ya  hay infrestructura serverless de contenedores, Fargate, ACI
- La infraestructura debe ser orquestada
- La API de k8s es el standard de facto
- Veremos mezcalde nnodos clasicos e infra serverless de contenedores orquestada bajo la misma API

## HPA

Escalado en base a otras metricas además de cpu y memoria
Proyecto de rhl que donaron **KEDA**

Keda amplia,permite desplegar ver todos los que tiene
podemos desplegar en base a inpusts de aws, azure, elasticsearch, etc

el crd
ScaledObject
keda crea un hpa 

## PROMETHEUS

P espera que el contenedor tenga un endpoint que le de todo el listado de metricas. Cada cierto tiempo llama a los endpoints de metricas, almacena y las guarda.
El formato es el de metricas de Prometheus. 
Se integra perfectaemnte en k8s. Puede utilizar la api de k8s para autodescubrir pods.


## HELM Y CUSTOMIZE

Facilita la instalación y mantenimiento de ficheros yaml

Esos yamls no permiten variables de entorno, esta grabado a fuego

**helm** es mas complejo, va con charts, bitnami hace charts.
Los instalas con un solo comando.
Con un fichero puedes modificar los datos por defecto de bitnami, por ejemplo

podemos crear charts de nuestros decpliegues




