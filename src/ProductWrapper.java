import java.util.Objects;


public class ProductWrapper {
    private Product product;
    public ProductWrapper(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product.getInternalCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ProductWrapper)) {
            return false;
        }
        ProductWrapper other = (ProductWrapper) obj;
        return Objects.equals(product.getInternalCode(), other.getProduct().getInternalCode());
    }
}
