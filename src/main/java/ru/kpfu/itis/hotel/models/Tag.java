package ru.kpfu.itis.hotel.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * 03.05.2022
 * Hotel
 *
 * @author Niyaz Khadimullin @BlackPrince163
 * 11-004
 */

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tagName;

    @ToString.Exclude
    @ManyToMany(mappedBy = "tags")
    private List<News> news;
}
