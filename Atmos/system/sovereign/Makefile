build-go:
	cd system/sovereign && go build -o sovereign_tool main.go

run-go: build-go
	./system/sovereign/sovereign_tool


interop:
	@echo "--- Starting Python Physics Engine ---"
	python -m atmos.main -a 12000 -j
	@echo "--- Starting Go Sovereign Scanner ---"
	go run system/sovereign/main.go

