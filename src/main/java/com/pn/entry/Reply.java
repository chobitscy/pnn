package com.pn.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Reply implements Serializable {

    @Id
    private String id;

    private Long uid;

    private String username;

    private Long vid;

    private String content;

    private Date date;
}
