package live.chotakr.chotakr.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ChotakrRequest {

    @NotNull
    private String url;
    private String id;
    private Long validFrom;
    private Long validTill;
//    private
}
