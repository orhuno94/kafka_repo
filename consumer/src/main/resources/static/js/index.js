var chart;
var config;
var timeFormat = 'DD/MM/YYYY HH:mm';

function newDate(days) {
	return moment().add(days, 'd').toDate();
}

function newDateString(days) {
	return moment().add(days, 'd').format(timeFormat);
}

var color = Chart.helpers.color;
var config = {
	type: 'line',
	data: {
		labels: [ // Date Objects
			
		],
		datasets: []
	},
	options: {
		title: {
			text: 'Chart.js Time Scale'
		},
		scales: {
			xAxes: [{
				type: 'time',
				time: {
					parser: timeFormat,
					// round: 'day'
					tooltipFormat: 'HH:mm'
				},
				scaleLabel: {
					display: true,
					labelString: 'Date'
				}
			}],
			yAxes: [{
				scaleLabel: {
					display: true,
					labelString: 'value'
				}
			}]
		},
	}
};


$( window ).ready(function() {
  connect();
});
window.onload = function() {
	var ctx = document.getElementById('canvas').getContext('2d');
	chart = new Chart(ctx, config);
};
function connect() {
  var socket = new SockJS('/websocket');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
      stompClient.subscribe('/topic/pushNotification', function (notification) {
          $('#textArea').val(handleData($.parseJSON(notification.body)));
       });
  });
}

		
function handleData(jsonobject){
	datasetindex=-1;
	for(index=0; index<chart.data.labels.length; index++){
		if(jsonobject.server==chart.data.labels[index])
			datasetindex=index;
			
	}
	if(datasetindex==-1)
		addDataset(jsonobject);
	
	addData(jsonobject);
}

function addDataset(jsonobject){
	//var colorName = colorNames[chart.data.datasets.length % colorNames.length];
			var newColor = "#7743CE";
			var newDataset = {
				label: jsonobject.server,
				borderColor: newColor,
				//backgroundColor: color(newColor).alpha(0.5).rgbString(),
				data: [],
			};

			for (var index = 0; index < chart.data.labels.length; ++index) {
				newDataset.data.push(0);
			}

			chart.data.datasets.push(newDataset);
			chart.update();
		}
		
function addData(jsonobject){
	if (chart.data.datasets.length > 0) {
				chart.data.labels.push(newDate(jsonobject.datetime));

				for (var index = 0; index < chart.data.datasets.length; ++index) {
					if (typeof chart.data.datasets[index].data[0] === 'object') {
						chart.data.datasets[index].data.push({
							x: newDate(jsonobject.datetime),
							y: 0,
						});
					} else {
						chart.data.datasets[index].data.push(1);
					}
				}

				chart.update();
			}
		}
function newDate(days) {
	return moment(days).toDate();
}

function newDateString(days) {
	return moment(days);
}
