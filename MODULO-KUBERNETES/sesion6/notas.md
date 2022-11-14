# Sesion 6

## Ciclo de vida de un pod

-pending<br>
-runnning<br>
-error, terminan con error<br>
-complete, terminan ok<br>
-evicted, faltan trecursos<br>

**restartpolicy**: Always, onFailure(solo si termian mal), Never

Estado contenedores, estar listo y estar corriendo, no es lo mismo.

Distingue entre running, live y ready<br>
ready -  lo gestiona la aplicación, esta vivo pero no puede atender peticiones, o bien porque depende de recursos que no estan disponibles o bien que está en un estado que le impide atender peticiones<br>
running - lo gestiona kubernetes <br>
live - lo gestiona la a aplicación, responde a las peticiones, ejem. bbdd responde las peticiones<br>

Si la aplicación no dice nadad k asume que está live y ready porque está running<br>

Si un contenedror no esta vivo será eliminado de la lista de endpoints por kubernetes.

**livenessProbe**, prueba de vida, puede ser:<br> 
- Con un proceso en el contenedor, podemos ejecutar decirle a k que ejecute cada cierto tiempo este proceso.<br>
- También puede ser abrir un socket.<br>
- Hacer llamada http GET, código que devuelve (Lo ideal es crear endpoints específicos para prueba de vida.
)<br>
- Llamada GRPC(Lo ideal es crear endpoints específicos para prueba de vida.
)<br>

**readinessProbe** prueba de estar listo, igual que la anterior


## Recursos

Se pueden definir peticiones y limites de recursos (CPU y mem)

peticiones: cantidad de recursos que necesita el contenedor en condiciones de baja carga

No hace balanceo de carga, considera los nodos que al menos tengan libre los recursos que los contenedores están pididendo
Con el tiempo de puede desestabilizar los nodos, y k no lo va a apañr, software de terceros<br>

Cluster autoschedule, un nodo que no encuentra un pod que tenga recursos (se queda en pending), aprovisiona un nuevo nodo.<br>

CPU va en milicores 50m = milesimas partes de un core.

Para saber recursos consumidos ahce falta el metrics

<pre>
angel@lemoncode:~$ k top node
error: Metrics API not available
</pre>

**HorixontalPodAutoscaler** (HPA)

*HPA* escalado en k es en porcentages de las la peticiones
HPA monitoriza las peticiones, saca medias, le establecemos objetivos que sea un   porcentaje de las peticiones 

cpu 50m HPA, escalamos al 200% de CPU, quiere decir que los pods de media esten ocupando 100m, ira matando todos hasta que consiga el objetivo de 100m en los pods que queden. 






