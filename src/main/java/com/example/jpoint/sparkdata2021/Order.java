package com.example.jpoint.sparkdata2021;

import com.example.unsafe_sparkdata.ForiegnKey;
import com.example.unsafe_sparkdata.Source;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Source("data/orders.csv")
public class Order {
    private String name;
    private String desk;
    private long price;
    private long criminalId;
}
