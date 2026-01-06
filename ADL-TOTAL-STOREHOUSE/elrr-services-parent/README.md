## Service and Aggregator Parent Project

### Why does this exist?

Aggregator and Services both need the same entity definitions to run. This parent project allows for the build of entities before the Aggregator and Services jars are built in CI/CD.

### To Run

You will need to clone this repo and then run the following commands to get the referenced submodules to synchronize:

```
git submodule init
git submodule update
```

or if you have `make` you can run

```
make sync
```
