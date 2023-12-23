package live.chotakr.chotakr.model;

import javax.validation.constraints.NotNull;

public record ChotakrRequest(
        @NotNull String url,
        String id,
        Long validFrom,
        Long validTill

) {}
