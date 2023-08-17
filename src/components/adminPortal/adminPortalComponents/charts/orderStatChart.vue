
<template>
    <adminHeader></adminHeader>
  
    <div id="adminPortalBody">
        <navBar></navBar>
        
        <div id="adminPortalBodyRight">
            <h1>Demographics</h1>
          <canvas ref="barChartCanvas"></canvas>
        </div>
    </div>
  </template>
  
  
  <script>
  
import { Chart, BarController, BarElement, LinearScale, CategoryScale } from 'chart.js';
import axios from 'axios';
import navBar from '../adminNavBar.vue'
import adminHeader from '../adminPortalHeader.vue'

Chart.register(BarController, BarElement, LinearScale, CategoryScale);

  export default {
    components: {
        navBar,
        adminHeader
    },
    data() {
      return {
        barChart: null,
        chartDetail:[],
        chartLabels:[],
        chartData:[]
      };
    },

    methods: {
      createBarChart() {
        console.log('chartLabels:', JSON.stringify(this.chartLabels));
  console.log('chartData:', this.chartData);

        const ctx = this.$refs.barChartCanvas.getContext('2d');
  
        this.barChart = new Chart(ctx, {
          type: 'bar',
          data: {
            labels: this.chartLabels,
            datasets: [
              {
                label: 'Bar Chart Data',
                backgroundColor: 'rgba(72 , 192 , 192 , 0.8)',
                data: this.chartData,
              },
            ],
          },
          options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
              y: {
                beginAtZero: true,
              },
            },
          },
        });
      },
    },

    mounted() {
      axios.get("http://localhost:8080/manageOrder/viewItemSales").then(response=>{
        // console.log(response)
        this.chartDetail = response.data
        // console.log(this.chartDetail)

        for(let i =0 ; i < this.chartDetail.length ; i++){
            this.chartLabels[i] = this.chartDetail[i].productId
            this.chartData[i] = this.chartDetail[i].quantity
        }

        console.log(this.chartLabels)
        console.log(this.chartData)
        this.createBarChart()

      }).catch(error=>{
        
        console.log(error)
      })


      
    },
  };

  </script>
  
  <style>
  canvas {
    max-width: 600px;
    padding-bottom: 100px;
    margin:  auto;
  }
  </style>
  