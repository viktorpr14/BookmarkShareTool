package org.softserveinc.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Compound primary key for UserConnection
 */
@Embeddable
public class UserConnectionId implements Serializable{
    private String userId;
    private String providerId;
    private String providerUserId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }
}
