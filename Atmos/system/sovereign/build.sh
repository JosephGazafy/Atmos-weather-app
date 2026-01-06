#!/bin/bash
echo "Cleaning up environment..."
rm -f main.go
echo "Rebuilding Go bridge..."
# (Insert your Go build commands here)
go build -o engine-bridge
echo "Done."
