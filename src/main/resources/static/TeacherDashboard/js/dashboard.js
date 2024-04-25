(function($) {
  'use strict';
  $(function() {

    Chart.defaults.global.legend.labels.usePointStyle = true;
    
    if ($("#serviceSaleProgress").length) {
      var bar = new ProgressBar.Circle(serviceSaleProgress, {
        color: 'url(#gradient)',
        // This has to be the same size as the maximum width to
        // prevent clipping
        strokeWidth: 8,
        trailWidth: 8,
        easing: 'easeInOut',
        duration: 1400,
        text: {
          autoStyleContainer: false
        },
        from: { color: '#aaa', width: 6 },
        to: { color: '#57c7d4', width: 6 }
      });

      bar.animate(.65);  // Number from 0.0 to 1.0
      bar.path.style.strokeLinecap = 'round';
      let linearGradient = '<defs><linearGradient id="gradient" x1="0%" y1="0%" x2="100%" y2="0%" gradientUnits="userSpaceOnUse"><stop offset="20%" stop-color="#da8cff"/><stop offset="50%" stop-color="#9a55ff"/></linearGradient></defs>';
      bar.svg.insertAdjacentHTML('afterBegin', linearGradient);
    }
    if ($("#productSaleProgress").length) {
      var bar = new ProgressBar.Circle(productSaleProgress, {
        color: 'url(#productGradient)',
        // This has to be the same size as the maximum width to
        // prevent clipping
        strokeWidth: 8,
        trailWidth: 8,
        easing: 'easeInOut',
        duration: 1400,
        text: {
          autoStyleContainer: false
        },
        from: { color: '#aaa', width: 6 },
        to: { color: '#57c7d4', width: 6 }
      });

      bar.animate(.6);  // Number from 0.0 to 1.0
      bar.path.style.strokeLinecap = 'round';
      let linearGradient = '<defs><linearGradient id="productGradient" x1="0%" y1="0%" x2="100%" y2="0%" gradientUnits="userSpaceOnUse"><stop offset="40%" stop-color="#36d7e8"/><stop offset="70%" stop-color="#b194fa"/></linearGradient></defs>';
      bar.svg.insertAdjacentHTML('afterBegin', linearGradient);
    }
    if ($("#points-chart").length) {
      var ctx = document.getElementById('points-chart').getContext("2d");

      var gradientStrokeViolet = ctx.createLinearGradient(0, 0, 0, 181);
      gradientStrokeViolet.addColorStop(0, 'rgba(218, 140, 255, 1)');
      gradientStrokeViolet.addColorStop(1, 'rgba(154, 85, 255, 1)');

      var myChart = new Chart(ctx, {
          type: 'bar',
          data: {
              labels: [1, 2, 3, 4, 5, 6, 7, 8],
              datasets: [
                {
                  label: "North Zone",
                  borderColor: gradientStrokeViolet,
                  backgroundColor: gradientStrokeViolet,
                  hoverBackgroundColor: gradientStrokeViolet,
                  pointRadius: 0,
                  fill: false,
                  borderWidth: 1,
                  fill: 'origin',
                  data: [20, 40, 15, 35, 25, 50, 30, 20]
                },
                {
                  label: "South Zone",
                  borderColor: '#e9eaee',
                  backgroundColor: '#e9eaee',
                  hoverBackgroundColor: '#e9eaee',
                  pointRadius: 0,
                  fill: false,
                  borderWidth: 1,
                  fill: 'origin',
                  data: [40, 30, 20, 10, 50, 15, 35, 20]
                }
            ]
          },
          options: {
              legend: {
                  display: false
              },
              scales: {
                  yAxes: [{
                      ticks: {
                          display: false,
                          min: 0,
                          stepSize: 10
                      },
                      gridLines: {
                        drawBorder: false,
                        display: false
                      }
                  }],
                  xAxes: [{
                      gridLines: {
                        display:false,
                        drawBorder: false,
                        color: 'rgba(0,0,0,1)',
                        zeroLineColor: '#eeeeee'
                      },
                      ticks: {
                          padding: 20,
                          fontColor: "#9c9fa6",
                          autoSkip: true,
                      },
                      barPercentage: 0.7
                  }]
                }
              },
              elements: {
                point: {
                  radius: 0
                }
              }
            })
    }
    if ($("#events-chart").length) {
      var ctx = document.getElementById('events-chart').getContext("2d");

      var gradientStrokeBlue = ctx.createLinearGradient(0, 0, 0, 181);
      gradientStrokeBlue.addColorStop(0, 'rgba(54, 215, 232, 1)');
      gradientStrokeBlue.addColorStop(1, 'rgba(54, 215, 232, 1)');

      var myChart = new Chart(ctx, {
          type: 'bar',
          data: {
              labels: ['Jan',' Feb', 'Mar','Apr', 'May', 'June', 'July', 'Aug'],
              datasets: [
                {
                  label: "Hours",
                  borderColor: gradientStrokeBlue,
                  backgroundColor: gradientStrokeBlue,
                  hoverBackgroundColor: gradientStrokeBlue,
                  pointRadius: 0,
                  fill: false,
                  borderWidth: 0,
                  // fill: 'origin',
                  data: [220, 90, 115, 135, 85, 150, 130, 230]
                }// },
                // {
                //   label: "International",
                //   borderColor: '#e9eaee',
                //   backgroundColor: '#e9eaee',
                //   hoverBackgroundColor: '#e9eaee',
                //   pointRadius: 0,
                //   fill: false,
                //   borderWidth: 1,
                //   fill: 'origin',
                //   data: [40, 30, 20, 10, 50, 15, 35, 20]
                // }
            ]
          },
          options: {
            
              legend: {
                  display: false
              },
              scales: {
                  yAxes: [{
                      ticks: {
                          display: false,
                          min: 0,
                          stepSize: 1
                      },
                      gridLines: {
                        drawBorder: false,
                        display: false
                      }
                  }],
                  xAxes: [{
                      gridLines: {
                        display:false,
                        drawBorder: false,
                        color: 'rgba(0,0,0,1)',
                        zeroLineColor: '#eeeeee'
                      },
                      ticks: {
                          padding: 20,
                          fontColor: "#9c9fa6",
                          autoSkip: true,
                      },
                      barPercentage: 0.7
                  }]
                }
              },
              elements: {
                point: {
                  radius: 0
                }
              }
            })
    }
    if ($("#visit-sale-chart").length) {
      Chart.defaults.global.legend.labels.usePointStyle = true;
      var ctx = document.getElementById('visit-sale-chart').getContext("2d");

      var gradientStrokeViolet = ctx.createLinearGradient(0, 0, 0, 181);
      gradientStrokeViolet.addColorStop(0, 'rgba(145, 199, 212, 0.667)');
      gradientStrokeViolet.addColorStop(1, 'rgba(145, 199, 212, 0.667)');
      var gradientLegendViolet = 'linear-gradient(to right, rgba(145, 199, 212, 0.667), rgba(145, 199, 212, 0.667))';
      
      var gradientStrokeBlue = ctx.createLinearGradient(0, 0, 0, 360);
      gradientStrokeBlue.addColorStop(0, 'rgba(54, 215, 232, 1)');
      gradientStrokeBlue.addColorStop(1, 'rgba(54, 215, 232, 1');
      var gradientLegendBlue = 'linear-gradient(to right, rgba(54, 215, 232, 1), rgba(54, 215, 232, 1))';

      var gradientStrokeRed = ctx.createLinearGradient(0, 0, 0, 300);
      gradientStrokeRed.addColorStop(0, 'rgba(255, 191, 150, 1)');
      gradientStrokeRed.addColorStop(1, 'rgba(255, 191, 150, 1)');
      var gradientLegendRed = 'linear-gradient(to right, rgba(255, 191, 150, 1), rgba(255, 191, 150, 1))';

      var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['JAN', 'FEB', 'MAR','APR'],
            datasets: [
              {
                label: "Absent in days",
                borderColor: gradientStrokeViolet,
                backgroundColor: gradientStrokeViolet,
                hoverBackgroundColor: gradientStrokeViolet,
                legendColor: gradientLegendViolet,
                pointRadius: 0,
                fill: false,
                borderWidth: 1,
                fill: 'origin',
                data: [2, 3, 5, 1]
              },
              {
                label: "Present in Days",
                borderColor: gradientStrokeRed,
                backgroundColor: gradientStrokeRed,
                hoverBackgroundColor: gradientStrokeRed,
                legendColor: gradientLegendRed,
                pointRadius: 0,
                fill: false,
                borderWidth: 1,
                fill: 'origin',
                data: [20, 19, 18, 25]
              },
              {
                label: "Completed courses",
                borderColor: gradientStrokeBlue,
                backgroundColor: gradientStrokeBlue,
                hoverBackgroundColor: gradientStrokeBlue,
                legendColor: gradientLegendBlue,
                pointRadius: 0,
                fill: false,
                borderWidth: 1,
                fill: 'origin',
                data: [7, 1, 3, 4]
              }
          ]
        },
        options: {
          responsive: true,
          legend: false,
          legendCallback: function(chart) {
            var text = []; 
            text.push('<ul>'); 
            for (var i = 0; i < chart.data.datasets.length; i++) { 
                text.push('<li><span class="legend-dots" style="background:' + 
                           chart.data.datasets[i].legendColor + 
                           '"></span>'); 
                if (chart.data.datasets[i].label) { 
                    text.push(chart.data.datasets[i].label); 
                } 
                text.push('</li>'); 
            } 
            text.push('</ul>'); 
            return text.join('');
          },
          scales: {
              yAxes: [{
                  ticks: {
                      display: false,
                      min: 0,
                      stepSize: 20,
                      max: 31
                  }
              }],
              xAxes: [{
                  gridLines: {
                    // display:false,
                    drawBorder: false,
                    color: 'rgba(0,0,0,1)',
                    zeroLineColor: 'rgba(235,237,242,1)'
                  },
                  ticks: {
                      padding: 20,
                      fontColor: "#9c9fa6",
                      autoSkip: true,
                  },
                  categoryPercentage: 0.5,
                  barPercentage: 0.5
              }]
            }
          },
          elements: {
            point: {
              radius: 0
            }
          }
      })
      $("#visit-sale-chart-legend").html(myChart.generateLegend());
    }
    if ($("#student-avg-marks").length) {
      Chart.defaults.global.legend.labels.usePointStyle = true;
      var ctx = document.getElementById('student-avg-marks').getContext("2d");

      var gradientStrokeViolet = ctx.createLinearGradient(0, 0, 0, 181);
      gradientStrokeViolet.addColorStop(0, 'rgba(178,178,229,1)');
      gradientStrokeViolet.addColorStop(1, 'rgba(178,178,229,1)');
      var gradientLegendViolet = 'linear-gradient(to right, rgba(218, 140, 255, 1), rgba(154, 85, 255, 1))';
      
      // var gradientStrokeBlue = ctx.createLinearGradient(0, 0, 0, 360);
      // gradientStrokeBlue.addColorStop(0, 'rgba(54, 215, 232, 1)');
      // gradientStrokeBlue.addColorStop(1, 'rgba(177, 148, 250, 1)');
      // var gradientLegendBlue = 'linear-gradient(to right, rgba(54, 215, 232, 1), rgba(177, 148, 250, 1))';

      // var gradientStrokeRed = ctx.createLinearGradient(0, 0, 0, 300);
      // gradientStrokeRed.addColorStop(0, 'rgba(255, 191, 150, 1)');
      // gradientStrokeRed.addColorStop(1, 'rgba(254, 112, 150, 1)');
      // var gradientLegendRed = 'linear-gradient(to right, rgba(255, 191, 150, 1), rgba(254, 112, 150, 1))';

      var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: ['QA', 'Java', 'C', 'Python', 'HTML/CSS', 'PHP', 'Testing', 'Django'],
            datasets: [
              {
                label: "Marks",
                borderColor: gradientStrokeViolet,
                backgroundColor: gradientStrokeViolet,
                hoverBackgroundColor: gradientStrokeViolet,
                legendColor: gradientLegendViolet,
                
                fill: false,
               
                data: [5 ,20, 70, 15, 35, 75, 90, 30, 20]
              }
              // {
              //   label: "USA",
              //   borderColor: gradientStrokeRed,
              //   backgroundColor: gradientStrokeRed,
              //   hoverBackgroundColor: gradientStrokeRed,
              //   legendColor: gradientLegendRed,
              //   pointRadius: 0,
              //   fill: false,
              //   borderWidth: 1,
              //   fill: 'origin',
              //   data: [40, 30, 20, 10, 50, 15, 35, 40]
              // },
              // {
              //   label: "UK",
              //   borderColor: gradientStrokeBlue,
              //   backgroundColor: gradientStrokeBlue,
              //   hoverBackgroundColor: gradientStrokeBlue,
              //   legendColor: gradientLegendBlue,
              //   pointRadius: 0,
              //   fill: false,
              //   borderWidth: 1,
              //   fill: 'origin',
              //   data: [70, 10, 30, 40, 25, 50, 15, 30]
              // }
          ]
        },
        options: {
          responsive: true,
          legend: false,
          legendCallback: function(chart) {
            var text = []; 
            text.push('<ul>'); 
            for (var i = 0; i < chart.data.datasets.length; i++) { 
                text.push('<li><span class="legend-dots" style="background:' + 
                           chart.data.datasets[i].legendColor + 
                           '"></span>'); 
                if (chart.data.datasets[i].label) { 
                    text.push(chart.data.datasets[i].label); 
                } 
                text.push('</li>'); 
            } 
            text.push('</ul>'); 
            return text.join('');
          },
          scales: {
              yAxes: [{
                  ticks: {
                      display: false,
                      min: 0,
                      stepSize: 15,
                      max: 100
                  },
                  gridLines: {
                    // drawBorder: false,
                    color: '#dcdaf0',
                    zeroLineColor: '#dcdaf0'
                  }
              }],
              xAxes: [{
                  gridLines: {
                    display:false,
                    drawBorder: false,
                    // color: 'rgba(0,0,0,1)',
                    // zeroLineColor: 'rgba(235,237,242,1)'
                  },
                  ticks: {
                      padding: 20,
                      fontColor: "#9c9fa6",
                      autoSkip: true,
                  },
                  categoryPercentage: 0.5,
                  barPercentage: 0.5
              }]
            }
          },
          elements: {
            point: {
              radius: 0
            }
          }
      })
      $("#student-avg-marks-legend").html(myChart.generateLegend());
    }
    if ($("#feedback-frm-students").length) {
      Chart.defaults.global.legend.labels.usePointStyle = true;
      var ctx = document.getElementById('feedback-frm-students').getContext("2d");

      var gradientStrokeViolet = ctx.createLinearGradient(0, 0, 0, 181);
      gradientStrokeViolet.addColorStop(0, 'rgba(190,201,185,1)');
      gradientStrokeViolet.addColorStop(1, 'rgba(190,201,185,1)');
      var gradientLegendViolet = 'linear-gradient(to right, rgba(159,159,188,1), rgba(159,159,188,1))';
      
      // var gradientStrokeBlue = ctx.createLinearGradient(0, 0, 0, 360);
      // gradientStrokeBlue.addColorStop(0, 'rgba(54, 215, 232, 1)');
      // gradientStrokeBlue.addColorStop(1, 'rgba(177, 148, 250, 1)');
      // var gradientLegendBlue = 'linear-gradient(to right, rgba(54, 215, 232, 1), rgba(177, 148, 250, 1))';

      // var gradientStrokeRed = ctx.createLinearGradient(0, 0, 0, 300);
      // gradientStrokeRed.addColorStop(0, 'rgba(255, 191, 150, 1)');
      // gradientStrokeRed.addColorStop(1, 'rgba(254, 112, 150, 1)');
      // var gradientLegendRed = 'linear-gradient(to right, rgba(255, 191, 150, 1), rgba(254, 112, 150, 1))';

      var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['Java batch 1', 'PHP batch 6', 'HTML Batch 2', 'JavaScript batch 9','Java batch 5', 'PHP batch 3', 'HTML Batch 8', 'JavaScript batch 2'],
            datasets: [
              {
                label: "Feedbacks",
                borderColor: gradientStrokeViolet,
                backgroundColor: gradientStrokeViolet,
                hoverBackgroundColor: gradientStrokeViolet,
                legendColor: gradientLegendViolet,
                
                fill: false,
               
                data: [4.5 ,2.0, 5.0, 1.5,9.5 ,2.9, 5.0, 2.5]
              }
              // {
              //   label: "USA",
              //   borderColor: gradientStrokeRed,
              //   backgroundColor: gradientStrokeRed,
              //   hoverBackgroundColor: gradientStrokeRed,
              //   legendColor: gradientLegendRed,
              //   pointRadius: 0,
              //   fill: false,
              //   borderWidth: 1,
              //   fill: 'origin',
              //   data: [40, 30, 20, 10, 50, 15, 35, 40]
              // },
              // {
              //   label: "UK",
              //   borderColor: gradientStrokeBlue,
              //   backgroundColor: gradientStrokeBlue,
              //   hoverBackgroundColor: gradientStrokeBlue,
              //   legendColor: gradientLegendBlue,
              //   pointRadius: 0,
              //   fill: false,
              //   borderWidth: 1,
              //   fill: 'origin',
              //   data: [70, 10, 30, 40, 25, 50, 15, 30]
              // }
          ]
        },
        options: {
          responsive: true,
          legend: false,
          legendCallback: function(chart) {
            var text = []; 
            text.push('<ul>'); 
            for (var i = 0; i < chart.data.datasets.length; i++) { 
                text.push('<li><span class="legend-dots" style="background:' + 
                           chart.data.datasets[i].legendColor + 
                           '"></span>'); 
                if (chart.data.datasets[i].label) { 
                    text.push(chart.data.datasets[i].label); 
                } 
                text.push('</li>'); 
            } 
            text.push('</ul>'); 
            return text.join('');
          },
          scales: {
              yAxes: [{
                  ticks: {
                      display: false,
                      min: 0,
                      stepSize: 20,
                      max: 5.0
                  },
                  gridLines: {
                    drawBorder: false,
                    color: '#322f2f',
                    zeroLineColor: '#322f2f'
                  }
              }],
              xAxes: [{
                  gridLines: {
                    display:false,
                    drawBorder: false,
                    color: 'rgba(0,0,0,1)',
                    zeroLineColor: 'rgba(235,237,242,1)'
                  },
                  ticks: {
                      padding: 20,
                      fontColor: "#9c9fa6",
                      autoSkip: true,
                  },
                  categoryPercentage: 0.5,
                  barPercentage: 0.5
              }]
            }
          },
          elements: {
            point: {
              radius: 0
            }
          }
      })
      $("#feedback-frm-students-legend").html(myChart.generateLegend());
    }
    if ($("#traffic-chart").length) {
      var gradientStrokeBlue = ctx.createLinearGradient(0, 0, 0, 10);
      gradientStrokeBlue.addColorStop(0, 'rgba(233,171,159,1)');
      gradientStrokeBlue.addColorStop(1, 'rgba(233,171,159,1)');
      var gradientLegendBlue = 'linear-gradient(to right, rgba(218, 201, 230, 1), rgba(218, 201, 230, 1))';

      var gradientStrokeRed = ctx.createLinearGradient(0, 0, 0, 50);
      gradientStrokeRed.addColorStop(0, 'rgba(79,192,231,1)');
      gradientStrokeRed.addColorStop(1, 'rgba(79,192,231,1)');
      var gradientLegendRed = 'linear-gradient(to right, rgba(141, 231, 171, 1), rgba(141, 231, 171, 1))';

      var gradientStrokeGreen = ctx.createLinearGradient(0, 0, 0, 30);
      gradientStrokeGreen.addColorStop(0, 'rgba(177,246,142,1)');
      gradientStrokeGreen.addColorStop(1, 'rgba(177,246,142,1)');
      var gradientLegendGreen = 'linear-gradient(to right, rgba(171, 179, 173, 0.806), rgba(171, 179, 173, 0.806))';      

      var trafficChartData = {
        datasets: [{
          data: [30, 40, 50],
          backgroundColor: [
            gradientStrokeBlue,
            gradientStrokeGreen,
            gradientStrokeRed
          ],
          hoverBackgroundColor: [
            gradientStrokeBlue,
            gradientStrokeGreen,
            gradientStrokeRed
          ],
          borderColor: [
            gradientStrokeBlue,
            gradientStrokeGreen,
            gradientStrokeRed
          ],
          legendColor: [
            gradientLegendBlue,
            gradientLegendGreen,
            gradientLegendRed
          ]
        }],
    
        // These labels appear in the legend and in the tooltips when hovering different arcs
        labels: [
          'Java : ',
          'PHP :  ',
          'JavaScript : ',
          'Object Orianted Programming : ',
          'Springboot  : ',
        ]
      };
      var trafficChartOptions = {
        responsive: true,
        animation: {
          animateScale: true,
          animateRotate: true
        },
        legend: false,
        legendCallback: function(chart) {
          var text = []; 
          text.push('<ul>'); 
          for (var i = 0; i < trafficChartData.datasets[0].data.length; i++) { 
              text.push('<li><span class="legend-dots" style="background:' + 
              trafficChartData.datasets[0].legendColor[i] + 
                          '"></span>'); 
              if (trafficChartData.labels[i]) { 
                  text.push(trafficChartData.labels[i]); 
              }
              text.push('<span class="float-right">'+trafficChartData.datasets[0].data[i]+"%"+'</span>')
              text.push('</li>'); 
          } 
          text.push('</ul>'); 
          return text.join('');
        }
      };
      var trafficChartCanvas = $("#traffic-chart").get(0).getContext("2d");
      var trafficChart = new Chart(trafficChartCanvas, {
        type: 'pie',
        data: trafficChartData,
        options: trafficChartOptions
      });
      $("#traffic-chart-legend").html(trafficChart.generateLegend());      
    }
    if ($("#inline-datepicker").length) {
      $('#inline-datepicker').datepicker({
        enableOnReadonly: true,
        todayHighlight: true,
      });
    }
  });
})(jQuery);