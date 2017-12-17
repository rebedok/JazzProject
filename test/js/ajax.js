$(document).ready(function() {

	$('#createRobot').on('click', function() {
		$.ajax({
			url: 'http://localhost:8080/api/robot/create',
			headers: {
			'X-Requested-With': 'XMLHttpRequest'
			},
			type: 'POST',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify({
				'id': 1,
				'isFree': true
			})
    	}).done(function(v) {
    		console.log(v);
    		$('#output').text(JSON.stringify(v));
		}).fail(function(v) {
			$('#output').text(v);
		});
	});


	$('#newJob').on('click', function() {
		var id = $('#some-field-1').val();
		$.ajax({
			url: 'http://localhost:8080/api/robot' + ((id > 0) ? '/'+ id:"" ),
			headers: {
			'X-Requested-With': 'XMLHttpRequest'
			},
			type: 'POST',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify({
				'id': 1,
				'isFree': false
			})
    	}).done(function(v) {
    		console.log(v);
    		$('#output').text(JSON.stringify(v));
		}).fail(function(v) {
			$('#output').text(v);
		});
	});
	

	$('#showRobot').on('click', function() {
		var id = $('#some-field-1').val();
		$.ajax({
			url: 'http://localhost:8080/api/robot' + ((id > 0) ? '/'+ id:"" ),
			headers: {
			'X-Requested-With': 'XMLHttpRequest'
			},
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
    	}).done(function(v) {
    		console.log(v);
    		$('#output').text(JSON.stringify(v));
		}).fail(function(v) {
			$('#output').text(v);
		});
	});

	$('#destroyRobot').on('click', function() {
		var id = $('#some-field-1').val();
		$.ajax({
			url: 'http://localhost:8080/api/robot' + ((id > 0) ? '/'+ id:"" ),
			headers: {
			'X-Requested-With': 'XMLHttpRequest'
			},
			type: 'DELETE',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
    	}).done(function(v) {
    		$('#output').text('OK');
		}).fail(function(v) {
			$('#output').text(v);
		});
	});



	setInterval(function() { 
		$.ajax({
			url: 'http://localhost:8080/api/log',
			headers: {
			'X-Requested-With': 'XMLHttpRequest'
			},
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json; charset=utf-8',
    	}).done(function(v) {
    		console.log(v);
    		$('#output').text(JSON.stringify(v));
		}).fail(function(v) {
			$('#output').text(v);
		}); 
	}, 5000);

});