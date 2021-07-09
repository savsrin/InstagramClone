package com.example.instagramclone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.instagramclone.databinding.ActivityMainBinding;
import com.example.instagramclone.fragments.ComposeFragment;
import com.example.instagramclone.fragments.PostsFragment;
import com.example.instagramclone.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    ActivityMainBinding binding;
    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Toast.makeText(MainActivity.this, "Home!", Toast.LENGTH_SHORT).show();
                        fragment = new PostsFragment();

                        break;
                    case R.id.action_compose:
                        Toast.makeText(MainActivity.this, "Compose!", Toast.LENGTH_SHORT).show();
                        fragment = new ComposeFragment();

                        break;
                    case R.id.action_profile:
                    default:
                        Toast.makeText(MainActivity.this, "Profile!", Toast.LENGTH_SHORT).show();
                        fragment = new ProfileFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }

        });
        // Set default selection
        binding.bottomNavigationView.setSelectedItemId(R.id.action_home);


//        // Allow user to logout
//        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                logoutUser();
//            }
//        });
//
//        binding.btnFeed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, FeedActivity.class);
//                startActivity(intent);
//            }
//        });


    }

    private void logoutUser() {
        ParseUser.logOut();
        finish();
    }
}








//
//    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 42;
//    public static final String TAG = "MainActivity";
//    final static int ON_RESUME_KEY = 22;
//    public String photoFileName = "photo.jpg";
////    private EditText etDescription;
////    private Button btnCaptureImage;
////    private ImageView ivPostImage;
////    private Button btnSubmit;
////    private Button btnLogout;
////    private Button btnFeed;
//    private File photoFile;
////    private BottomNavigationView bottomNavigationView;
//
//    ActivityMainBinding binding;
//
//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        // pause_val is a sentinel letting us know if an image was uploaded or not
//        int pause_val = binding.ivPostImage.getDrawable() == null ? 0 : 1;
//        outState.putInt(String.valueOf(ON_RESUME_KEY), pause_val);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//       // setContentView(R.layout.activity_main);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
////        etDescription = findViewById(R.id.etDescription);
////        btnCaptureImage = findViewById(R.id.btnCaptureImage);
////        ivPostImage = findViewById(R.id.ivPostImage);
////        btnSubmit = findViewById(R.id.btnSubmit);
////        btnLogout = findViewById(R.id.btnLogout);
////        btnFeed = findViewById(R.id.btnFeed);
////        bottomNavigationView = findViewById(R.id.bottom_navigation);
//
//
//        if (savedInstanceState != null && savedInstanceState.getInt(String.valueOf(ON_RESUME_KEY)) == 1) {
//            // allows for rotation
//            photoFile = getPhotoFileUri("photo_resized.jpg");
//            Bitmap takenImage = rotateBitmapOrientation(photoFile.getAbsolutePath());
//            ImageView ivPreview = (ImageView)  binding.ivPostImage;
//            ivPreview.setImageBitmap(takenImage);
//        }
//
////        if (savedInstanceState.getBoolean("photoFile")) {
////            ivPostImage = (ImageView) (savedInstanceState.getString("photoFile"));
////        }
//
//        // Allow user to logout
//        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                logoutUser();
//            }
//        });
//
//        binding.btnCaptureImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                launchCamera();
//
//
//            }
//        });
//
//        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String description = binding.etDescription.getText().toString();
//                if (description.isEmpty()) {
//                    Toast.makeText(
//                            MainActivity.this,
//                            "Description cannot be empty",
//                            Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (photoFile == null || binding.ivPostImage.getDrawable() == null) {
//                    Toast.makeText(MainActivity.this, "There is no image!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                ParseUser currentUser = ParseUser.getCurrentUser();
//                savePost(description, currentUser, photoFile);
//
//
//
//            }
//        });
//
//        binding.btnFeed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, FeedActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment fragment;
//                switch (item.getItemId()) {
//                    case R.id.action_home:
//                        Toast.makeText(MainActivity.this, "Home!", Toast.LENGTH_SHORT).show();
//
//                        break;
//                    case R.id.action_compose:
//                        Toast.makeText(MainActivity.this, "Compose!", Toast.LENGTH_SHORT).show();
//
//                        break;
//                    case R.id.action_profile:
//                    default:
//                        Toast.makeText(MainActivity.this, "Profile!", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//                return true;
//            }
//
//        });
//
//
//
//
//
//    }
//
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                // by this point we have the camera photo on disk
//                photoFile = getPhotoFileUri(photoFileName);
//                Bitmap takenImage = rotateBitmapOrientation(photoFile.getAbsolutePath());
//
//                        //BitmapFactory.decodeFile(photoFile.getAbsolutePath());
//                // RESIZE BITMAP, see section below
//                Bitmap resizedBitmap = BitmapScaler.scaleToFitWidth(takenImage, 500);
//                // Configure byte output stream
//                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//                // Compress the image further
//                resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
//                // Create a new file for the resized bitmap (`getPhotoFileUri` defined above)
//                File resizedFile = getPhotoFileUri("photo_resized.jpg");
//                try {
//                    resizedFile.createNewFile();
//                    FileOutputStream fos = null;
//                    fos = new FileOutputStream(resizedFile);
//                    fos.write(bytes.toByteArray());
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                // Load the taken image into a preview
//                photoFile = resizedFile;
//
//                ImageView ivPreview = (ImageView) findViewById(R.id.ivPostImage);
//                ivPreview.setImageBitmap(resizedBitmap);
//            } else { // Result was a failure
//                Toast.makeText(this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//
//    private Bitmap rotateBitmapOrientation(String photoFilePath) {
//        // Create and configure BitmapFactory
//        BitmapFactory.Options bounds = new BitmapFactory.Options();
//        bounds.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(photoFilePath, bounds);
//        BitmapFactory.Options opts = new BitmapFactory.Options();
//        Bitmap bm = BitmapFactory.decodeFile(photoFilePath, opts);
//        // Read EXIF Data
//        ExifInterface exif = null;
//        try {
//            exif = new ExifInterface(photoFilePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
//        int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
//        int rotationAngle = 0;
//        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
//        if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
//        if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
//        // Rotate Bitmap
//        Matrix matrix = new Matrix();
//        matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
//        Bitmap rotatedBitmap = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
//        // Return result
//        return rotatedBitmap;
//    }
//
//    private void launchCamera() {
//        // create Intent to take a picture and return control to the calling application
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        // Create a File reference for future access
//        photoFile = getPhotoFileUri(photoFileName);
//
//        // wrap File object into a content provider
//        // required for API >= 24
//        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
//        Uri fileProvider = FileProvider.getUriForFile(MainActivity.this, "com.codepath.fileprovider", photoFile);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);
//
//        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
//        // So as long as the result is not null, it's safe to use the intent.
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            // Start the image capture intent to take photo
//            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
//        }
//    }
//
//
//    // Returns the File for a photo stored on disk given the fileName
//    private File getPhotoFileUri(String photoFileName) {
//        // Get safe storage directory for photos
//        // Use `getExternalFilesDir` on Context to access package-specific directories.
//        // This way, we don't need to request external read/write runtime permissions.
//        File mediaStorageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);
//
//        // Create the storage directory if it does not exist
//        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
//            Log.d(TAG, "failed to create directory");
//        }
//
//        // Return the file target for the photo based on filename
//        File file = new File(mediaStorageDir.getPath() + File.separator + photoFileName);
//
//        return file;
//
//    }
//
//    private void savePost(String description, ParseUser currentUser, File photoFile) {
//        Post post = new Post();
//        post.setDescriotion(description);
//        post.setImage(new ParseFile(photoFile));
//        post.setUser(currentUser);
//        post.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e !=null) {
//                    Log.e(TAG, "Error while saving", e);
//                    Toast.makeText(MainActivity.this, "error while saving.", Toast.LENGTH_SHORT).show();
//                    return ;
//                }
//                Log.i(TAG, "Post save was successful!");
//                binding.etDescription.setText("");
//                binding.ivPostImage.setImageResource(0);
//            }
//        });
//    }
//
//    private void queryPosts() {
//        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
//        query.include(Post.KEY_USER);
//        query.findInBackground(new FindCallback<Post>() {
//            @Override
//            public void done(List<Post> posts, ParseException e) {
//                if (e != null) {
//                    Log.e(TAG, "Issue with getting posts", e);
//                    return;
//                }
//                for (Post post: posts) {
//                    Log.i(TAG, "Post: " + post.getDescription() + ", username: "
//                            + post.getUser().getUsername());
//                }
//
//            }
//        });
//    }
//
//    private void logoutUser() {
//        ParseUser.logOut();
//        finish();
//    }
//
//
//}