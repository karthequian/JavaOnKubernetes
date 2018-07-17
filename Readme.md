# Java App + Kubernetes demo

## Step 1: Take a look at Oracle Kubernetes Service
Start at: `https://console.us-ashburn-1.oraclecloud.com/`

You can run `kubectl get nodes` or a `kubectl get pods` to verify that things are up and running.

## Step 2: Deploy the Java Application
Let's look at the application real quick...

I've already published this container to `https://hub.docker.com/r/karthequian/javademo/`

Easily deploy the deployment and service with `kubectl apply -f deployment.yaml`

# Ingress: Let's add an ingress controller
We will use Heptio's contour with envoy as our ingress controller: `kubectl apply -f https://j.hept.io/contour-deployment-rbac`. You can also build your own if necessary.

Look at the data: `kubectl get all -n heptio-contour`

Run our ingress yaml: `kubectl apply -f ingress.yaml`

Take a look at the url: `<EXTERNAL-IP>/products`


# Prometheus section:
## Step 1: Deploy
Helm is the easiest way to do this. Check out `https://github.com/kubernetes/charts/tree/master/stable/prometheus`.

We can run `helm install stable/prometheus` to get the stock prometheus server.

In this case, we will run: `helm install stable/prometheus --name prom-demo -f values.yaml` to use our custom yaml.

## Step 2: See it running
Running `minikube service prom-demo-prometheus-server` will bring up the browser with prometheus server running.

## Step 3: Check out Kubernetes stats
Check out `kube_pod_status_phase` to see our all our pods running.

## Step 4: Prometheus operators
More info here: `https://prometheus.io/docs/prometheus/latest/querying/operators/` but you can use the query language to clean up the data point we looked at above.

`sum(kube_pod_status_phase) by (phase)` will return the set of pods that are running grouped by their phases.


## Step 5: Check out node information

The node exporter gives you node relative information as well like CPU/disk usage etc.

Run `count(node_cpu{mode="system", instance="192.168.99.100:9100"})` will return the cpu count which should match the number of CPU's in `kubectl describe nodes`

### Step 6: App metrics

1. Run the app: `kubectl apply -f twelve-clouds.yaml`
2. Visit the app after it's deployed: `minikube service twelve-clouds-service`
3. You'll see the  `/metrics` endpoint with go stats
4. Visit the `/hello` endpoint 2 times.
5. You'll see a new "hello_calls" metric in the dashboard