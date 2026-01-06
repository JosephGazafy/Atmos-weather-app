package main

import (
	"bufio"
	"encoding/json"
	"fmt"
	"os"
	"strconv"
)

type AtmosData struct {
	Timestamp string  `json:"timestamp"`
	Altitude  float64 `json:"altitude"`
	Density   float64 `json:"density"`
}

func main() {
	if len(os.Args) < 2 {
		fmt.Println("Usage: ./search_tool [DENSITY_THRESHOLD]")
		return
	}
	threshold, _ := strconv.ParseFloat(os.Args[1], 64)
	file, _ := os.Open("../../data.json")
	defer file.Close()

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		var d AtmosData
		json.Unmarshal(scanner.Bytes(), &d)
		if d.Density < threshold {
			fmt.Printf("⚠️ ALERT: Alt %.0fm | Density %.4f\n", d.Altitude, d.Density)
		}
	}
}

