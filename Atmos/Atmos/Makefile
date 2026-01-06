# Build native binaries for speed
build:
	@go build -o system/sovereign/search_tool system/sovereign/search.go
	@echo "⚡ Native Binaries Compiled."

# High-speed sweep
sweep:
	@for alt in 1000 2000 3000 4000 5000 6000 7000 8000 9000 10000; do \
		PYTHONPATH=src python -m atmos.main -a $$alt -j; \
	done
	@./health.sh

# Instant search using binary
search:
	@./system/sovereign/search_tool $(D)

