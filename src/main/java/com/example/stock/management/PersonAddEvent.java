package com.example.stock.management;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PersonAddEvent {
    private Person personDetails;
}
