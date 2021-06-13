package com.example.bridgelabz.bookstore.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bridgelabz.bookstore.R;
import com.example.bridgelabz.bookstore.Repository.BookRepository;
import com.example.bridgelabz.bookstore.SharedPreference;
import com.example.bridgelabz.bookstore.model.CartModel;

import java.math.BigDecimal;

public class CartItemViewHolder extends RecyclerView.ViewHolder {
    TextView bookCartTitle, bookCartAuthor, bookCartPrice, itemCountDisplay;
    ImageView bookCartImage,addBook,removeBook;
    public int itemCount = 1;
    public float bookPrice = 1;
    SharedPreference sharedPreference;
//    public static float totalPrice ;
    BookRepository bookRepository;
    private int cartPosition = 0;
    private CartModel cart;
    private CartBookClickListener cartBookClickListener;


    public CartItemViewHolder(@NonNull View itemView, CartBookClickListener cartBookClickListener) {
        super(itemView);
        bookCartTitle = itemView.findViewById(R.id.CartList_bookTitle);
        bookCartAuthor = itemView.findViewById(R.id.CartList_bookAuthor);
        bookCartImage = itemView.findViewById(R.id.CartList_bookImage);
        bookCartPrice =itemView.findViewById(R.id.CartList_bookPrice);
        itemCountDisplay = itemView.findViewById(R.id.textView3);
        addBook = itemView.findViewById(R.id.imageView2);
        removeBook = itemView.findViewById(R.id.imageView);
        sharedPreference = new SharedPreference(itemView.getContext());
        bookRepository = new BookRepository(itemView.getContext());
        this.cartBookClickListener = cartBookClickListener;

    }
    public void bind(CartModel cart, int cartPosition) {
        this.cart = cart;
        this.cartPosition = cartPosition;
        this.itemCount = cart.getQuantites();
        bookPrice = displayPrices(itemCount);
        bookCartTitle.setText(cart.getBook().getBookTitle());
        bookCartAuthor.setText(cart.getBook().getBookAuthor());
        bookCartPrice.setText(String.valueOf(bookPrice));
        itemCountDisplay.setText(String.valueOf(itemCount));
        String imageUri = cart.getBook().getBookImage();
        Glide.with(itemView.getContext())
                .load(imageUri)
                .into(bookCartImage);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // bookRepository.addBookToCart(cart.getBook().getBookID());
//                ItemCount++;
//                displayPrices(ItemCount);
//                itemCount.setText(String.valueOf(ItemCount));
//               //    bookPrice = cart.getBook().getBookPrice() * ItemCount;
//                bookCartPrice.setText(String.valueOf(totalBookPrice));
//                cartBookClickListener.onAddItemQuantity(cart);
                itemCount = itemCount + 1;
                updateUI(cart);
                cartBookClickListener.onAddItemQuantity(cart);
            }
        });

        removeBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //bookRepository.removeBookToCart(cart.getBook().getBookID());
//                ItemCount--;
//                displayPrices(ItemCount);
//                itemCount.setText(String.valueOf(ItemCount));
//                //bookPrice = cart.getBook().getBookPrice() * ItemCount;
////                totalPrice = bookPrice;
//                bookCartPrice.setText(String.valueOf(totalBookPrice));
//
//                cartBookClickListener.onMinusItemQuantity(cart, cartPosition);
//                if (ItemCount == 0){
////                    ObjectMapper mapper = new ObjectMapper();
////                    try {
////                        List<User> userList1 = mapper.readValue(new File(v.getContext().getFilesDir(),
////                                "Users.json"), new TypeReference<List<User>>(){});
////                        List<CartResponseModel> cartItems = userList1.get(sharedPreference.getPresentUserId()).getCartItemList();
////                        try {
////                            cartItems.remove(cart.getBook().getBookID());
////                        }catch (IndexOutOfBoundsException e){
////                            e.printStackTrace();
////                        }
////                        userList1.get(sharedPreference.getPresentUserId()).setCartItemList(cartItems);
////                        String updatedFile = mapper.writeValueAsString(userList1);
////                        FileOutputStream fos = v.getContext().openFileOutput("Users.json", Context.MODE_PRIVATE);
////                        fos.write(updatedFile.getBytes());
////                        fos.close();
////                    } catch (IOException e){
////                        e.printStackTrace();
////                    }
//                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                    Fragment myFragment = new CartFragment();
//                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();
//               }
                itemCount = itemCount - 1;
                updateUI(cart);
                cartBookClickListener.onMinusItemQuantity(cart, cartPosition);

            }

        });
    }


    private void updateUI(CartModel cart) {
        cart.setQuantites(itemCount);
        itemCountDisplay.setText(String.valueOf(itemCount));
        bookPrice = displayPrices(itemCount);
        bookCartPrice.setText(String.valueOf(bookPrice));
    }


    private float displayPrices(int item) {
        float bookPrice = cart.getBook().getBookPrice() * itemCount;
        return BigDecimal.valueOf(bookPrice).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
    }

}
