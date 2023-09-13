import java.util.List;

public class ExampleObject {
    private String firstName;
    private String subscriptionStatus;
    private List<String> services;

    public ExampleObject(String firstName, String subscriptionStatus, List<String> services) {
        this.firstName = firstName;
        this.subscriptionStatus = subscriptionStatus;
        this.services = services;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }
}
