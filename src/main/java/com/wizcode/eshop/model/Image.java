package com.wizcode.eshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;
    @Lob //Maps a large object (e.g., text, binary data) to a database column.
    private Blob image;
    private String downloadUrl;

    //Relationship
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
