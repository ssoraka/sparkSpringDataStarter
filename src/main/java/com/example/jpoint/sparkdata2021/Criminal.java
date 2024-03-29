package com.example.jpoint.sparkdata2021;

import com.example.unsafe_sparkdata.ForiegnKey;
import com.example.unsafe_sparkdata.Source;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Source("data/criminals.csv")
public class Criminal {
    private long id;
    private String name;
    private long number;

    @ForiegnKey("criminalId")
    private List<Order> orders;
}
