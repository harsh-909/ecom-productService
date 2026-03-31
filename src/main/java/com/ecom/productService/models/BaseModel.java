package com.ecom.productService.models;


import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Date;

@MappedSuperclass
public class BaseModel {
    @Id
    private long id;
    private Date createdAt;
    private Date lastUpdateAt;
    private boolean isDeleted;
}
