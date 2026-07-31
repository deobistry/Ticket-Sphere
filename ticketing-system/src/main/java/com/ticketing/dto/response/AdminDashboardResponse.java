package com.ticketing.dto.response;


import java.math.BigDecimal;


public class AdminDashboardResponse {


    private Long totalUsers;

    private Long totalTransportBookings;

    private Long totalAccommodationBookings;

    private BigDecimal totalRevenue;

    private Long confirmedBookings;

    private Long cancelledBookings;



    public AdminDashboardResponse(
            Long totalUsers,
            Long totalTransportBookings,
            Long totalAccommodationBookings,
            BigDecimal totalRevenue,
            Long confirmedBookings,
            Long cancelledBookings
    ){

        this.totalUsers = totalUsers;
        this.totalTransportBookings = totalTransportBookings;
        this.totalAccommodationBookings = totalAccommodationBookings;
        this.totalRevenue = totalRevenue;
        this.confirmedBookings = confirmedBookings;
        this.cancelledBookings = cancelledBookings;
    }



    public Long getTotalUsers() {
        return totalUsers;
    }


    public Long getTotalTransportBookings() {
        return totalTransportBookings;
    }


    public Long getTotalAccommodationBookings() {
        return totalAccommodationBookings;
    }


    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }


    public Long getConfirmedBookings() {
        return confirmedBookings;
    }


    public Long getCancelledBookings() {
        return cancelledBookings;
    }

}