	package com.ticketing.dto.request;
	
	import java.math.BigDecimal;
	import java.time.LocalDateTime;
	
	import com.ticketing.enums.TransportType;
	
	public class TransportationRequest {
	
	    private String transportNumber;
	
	    private TransportType type;
	
	    private String operatorName;
	
	    private String source;
	
	    private String destination;
	
	    private LocalDateTime departureTime;
	
	    private LocalDateTime arrivalTime;
	
	    private String duration;
	
	    private Integer totalSeats;
	
	    private Integer availableSeats;
	
	    private BigDecimal price;
	
	
	    public TransportationRequest() {
	    }
	
	
	    public TransportationRequest(
	            String transportNumber,
	            TransportType type,
	            String operatorName,
	            String source,
	            String destination,
	            LocalDateTime departureTime,
	            LocalDateTime arrivalTime,
	            String duration,
	            Integer totalSeats,
	            Integer availableSeats,
	            BigDecimal price
	    ) {
	        this.transportNumber = transportNumber;
	        this.type = type;
	        this.operatorName = operatorName;
	        this.source = source;
	        this.destination = destination;
	        this.departureTime = departureTime;
	        this.arrivalTime = arrivalTime;
	        this.duration = duration;
	        this.totalSeats = totalSeats;
	        this.availableSeats = availableSeats;
	        this.price = price;
	    }
	
	
	    public String getTransportNumber() {
	        return transportNumber;
	    }
	
	
	    public void setTransportNumber(String transportNumber) {
	        this.transportNumber = transportNumber;
	    }
	
	
	    public TransportType getType() {
	        return type;
	    }
	
	
	    public void setType(TransportType type) {
	        this.type = type;
	    }
	
	
	    public String getOperatorName() {
	        return operatorName;
	    }
	
	
	    public void setOperatorName(String operatorName) {
	        this.operatorName = operatorName;
	    }
	
	
	    public String getSource() {
	        return source;
	    }
	
	
	    public void setSource(String source) {
	        this.source = source;
	    }
	
	
	    public String getDestination() {
	        return destination;
	    }
	
	
	    public void setDestination(String destination) {
	        this.destination = destination;
	    }
	
	
	    public LocalDateTime getDepartureTime() {
	        return departureTime;
	    }
	
	
	    public void setDepartureTime(LocalDateTime departureTime) {
	        this.departureTime = departureTime;
	    }
	
	
	    public LocalDateTime getArrivalTime() {
	        return arrivalTime;
	    }
	
	
	    public void setArrivalTime(LocalDateTime arrivalTime) {
	        this.arrivalTime = arrivalTime;
	    }
	
	
	    public String getDuration() {
	        return duration;
	    }
	
	
	    public void setDuration(String duration) {
	        this.duration = duration;
	    }
	
	
	    public Integer getTotalSeats() {
	        return totalSeats;
	    }
	
	
	    public void setTotalSeats(Integer totalSeats) {
	        this.totalSeats = totalSeats;
	    }
	
	
	    public Integer getAvailableSeats() {
	        return availableSeats;
	    }
	
	
	    public void setAvailableSeats(Integer availableSeats) {
	        this.availableSeats = availableSeats;
	    }
	
	
	    public BigDecimal getPrice() {
	        return price;
	    }
	
	
	    public void setPrice(BigDecimal price) {
	        this.price = price;
	    }
	}