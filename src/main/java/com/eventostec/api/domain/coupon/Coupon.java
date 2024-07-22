package com.eventostec.api.domain.coupon;

import com.eventostec.api.domain.event.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "coupon")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon {

    @Id
    @GeneratedValue
    private UUID uuid;
    private Integer discount;
    private String valid;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public static Coupon buildCoupon(CouponRequestDTO couponRequestDTO, Event event){
        return Coupon.builder()
                .discount(couponRequestDTO.discount())
                .valid(couponRequestDTO.Valid())
                .event(event)
                .build();
    }
}
