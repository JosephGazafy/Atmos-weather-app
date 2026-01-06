package main

import (
	"bufio"
	"encoding/json"
	"fmt"
	"os"
)

type AtmosData struct {
	Altitude float64 `json:"altitude"`
	Pressure float64 `json:"pressure"`
	Density  float64 `json:"density"`
}

func main() {
	dataPath := "/data/data/com.termux/files/home/Atmos/Atmos/Atmos/data.json"

	file, err := os.Open(dataPath)
	if err != nil {
		fmt.Printf("❌ Data Link Broken: %v\n", err)
		return
	}
	defer file.Close()

	var totalPressure float64
	var count int
	scanner := bufio.NewScanner(file)

	fmt.Println("🚀 Analyzing Atmospheric History...")

	for scanner.Scan() {
		var d AtmosData
		if err := json.Unmarshal(scanner.Bytes(), &d); err == nil {
			totalPressure += d.Pressure
			count++
		}
	}

	if count > 0 {
		avgPressure := totalPressure / float64(count)
		fmt.Printf("📊 Records Analyzed: %d\n", count)
		fmt.Printf("🌡️  Average System Pressure: %.2f Pa\n", avgPressure)
	} else {
		fmt.Println("⚠️  No records found to analyze.")
	}
	fmt.Println("✅ Scan Complete.")
}

