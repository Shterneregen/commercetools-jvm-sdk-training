package handson.impl;

import io.sphere.sdk.carts.Cart;
import io.sphere.sdk.carts.CartDraftBuilder;
import io.sphere.sdk.carts.CartDraftDsl;
import io.sphere.sdk.carts.commands.CartCreateCommand;
import io.sphere.sdk.carts.commands.CartUpdateCommand;
import io.sphere.sdk.carts.commands.updateactions.AddDiscountCode;
import io.sphere.sdk.carts.commands.updateactions.AddLineItem;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.customers.Customer;
import io.sphere.sdk.models.DefaultCurrencyUnits;
import io.sphere.sdk.products.ProductProjection;

import java.util.concurrent.CompletionStage;

/**
 * This class provides operations to work with {@link Cart}s.
 */
public class CartService extends AbstractService {

    public CartService(SphereClient client) {
        super(client);
    }

    /**
     * Creates a cart for the given customer.
     *
     * @param customer the customer
     * @return the customer creation completion stage
     */
    public CompletionStage<Cart> createCart(final Customer customer) {
        // TODO 3.1. Create a cart
        CartDraftDsl cartDraftDsl = CartDraftBuilder.of(DefaultCurrencyUnits.EUR).customerId(customer.getId()).build();
        return client.execute(CartCreateCommand.of(cartDraftDsl));
    }

    /**
     * Adds the given product to the given cart.
     *
     * @param product the product
     * @param cart    the cart
     * @return the cart update completion stage
     */
    public CompletionStage<Cart> addProductToCart(final ProductProjection product, final Cart cart) {
        // TODO 3.2. Add line item to a cart
        AddLineItem addLineItem = AddLineItem.of(product.getId(), product.getMasterVariant().getId(), 1L);
        return client.execute(CartUpdateCommand.of(cart, addLineItem));
    }

    /**
     * Adds the given discount code to the given cart.
     *
     * @param code the discount code
     * @param cart the cart
     * @return the cart update completion stage
     */
    public CompletionStage<Cart> addDiscountToCart(final String code, final Cart cart) {
        // TODO 5.1 Add discount code to cart
        return client.execute(CartUpdateCommand.of(cart, AddDiscountCode.of(code)));
    }
}
