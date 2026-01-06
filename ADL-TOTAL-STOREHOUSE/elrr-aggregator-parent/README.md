## Aggregator Parent Project

### Why does this exist?

Aggregator and Services both need the same entity definitions to run. This parent project allows for the build of entities before the Aggregator jars are built in CI/CD.

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

### To update submodule

`cd` into the submodule and run `git pull`. It will update the latest of the branch (usually main). Then from the root perform a commit including the reference to the submodule.
