package dev.example.kinect.utils;

public class AdminPaths {
    public static class Trainee {
        public static final String PATH = "/trainees";
        public static final String ACTIVE = "/active";
        public static final String INACTIVE = "/inactive";
    }

    public static class Profile {
        public static final String PATH = "/profiles";
        public static final String PLANNING = "/planning";

    }

    public static class Gym {
        public static final String PATH = "/gyms";
    }
    public static class Offer {
        public static final String PATH= "/offers";
    }
    public static class Request {
        public static final String PATH = "/request";
        public static final String ACCEPT = "/accept";
        public static final String DENIED = "/denied";
        public static final String CANCEL = "/cancel";
    }
    public static class Role {
        public static final String PATH = "/roles";
    }
}
