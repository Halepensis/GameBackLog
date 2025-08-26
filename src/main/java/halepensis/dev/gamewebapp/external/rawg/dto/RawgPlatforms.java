package halepensis.dev.gamewebapp.external.rawg.dto;

import lombok.Data;

@Data
public class    RawgPlatforms {
    private Platform platform;
    private String released_at;
    private Requirments requirements;


    @Data
    public static class Platform {
        private int id;
        private String name;
        private String slug;

    }
    @Data
    public static class Requirments {
        private String minimum;
        private String recommended;
    }

}
