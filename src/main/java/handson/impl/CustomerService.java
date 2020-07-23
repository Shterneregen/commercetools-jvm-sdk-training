package handson.impl;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.customers.Customer;
import io.sphere.sdk.customers.CustomerDraftBuilder;
import io.sphere.sdk.customers.CustomerDraftDsl;
import io.sphere.sdk.customers.CustomerSignInResult;
import io.sphere.sdk.customers.CustomerToken;
import io.sphere.sdk.customers.commands.CustomerCreateCommand;
import io.sphere.sdk.customers.commands.CustomerCreateEmailTokenCommand;
import io.sphere.sdk.customers.commands.CustomerVerifyEmailCommand;

import java.util.concurrent.CompletionStage;

/**
 * This class provides operations to work with {@link Customer}s.
 */
public class CustomerService extends AbstractService {

    public CustomerService(final SphereClient client) {
        super(client);
    }

    /**
     * Creates a new customer {@link Customer} with the given parameters.
     *
     * @param email    the customers email
     * @param password the customers password
     * @return the customer creation completion stage
     */
    public CompletionStage<CustomerSignInResult> createCustomer(final String email, final String password) {
        // TODO 2.1 Create a customer
        final CustomerDraftDsl newCustomer = CustomerDraftBuilder.of(email, password).build();
        return client.execute(CustomerCreateCommand.of(newCustomer));
    }

    /**
     * Creates an email verification token for the given customer.
     * This is then used to create a password reset link.
     *
     * @param customer            the customer
     * @param timeToLiveInMinutes the time to live (in minutes) for the token
     * @return the customer token creation completion stage
     */
    public CompletionStage<CustomerToken> createEmailVerificationToken(final Customer customer, final Integer timeToLiveInMinutes) {
        // TODO 2.2 Create an email verification token
        return client.execute(CustomerCreateEmailTokenCommand.of(customer, timeToLiveInMinutes));
    }

    /**
     * Verifies the customer token.
     *
     * @param customerToken the customer token
     * @return the email verification completion stage
     */
    public CompletionStage<Customer> verifyEmail(final CustomerToken customerToken) {
        // TODO 2.3 Verify the customer token
        return client.execute(CustomerVerifyEmailCommand.ofCustomerToken(customerToken));
    }
}
