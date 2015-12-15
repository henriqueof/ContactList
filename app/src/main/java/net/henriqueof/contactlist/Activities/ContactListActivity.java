package net.henriqueof.contactlist.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import net.henriqueof.contactlist.Adapters.ContactsAdapter;
import net.henriqueof.contactlist.Helpers.DividerItemDecoration;
import net.henriqueof.contactlist.Models.Contact;
import net.henriqueof.contactlist.R;

import java.util.ArrayList;
import java.util.List;

public class ContactListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    /*
     * Defines an array that contains column names to move from
     * the Cursor to the ListView.
     */
    private final static String[] PROJECTION = {
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.LOOKUP_KEY,
    };

    // Defines the text expression
    private static final String SELECTION = ContactsContract.Contacts.DISPLAY_NAME + " LIKE ?";

    // Defines a variable for the search string
    private String mSearchString;
    // Defines the array to hold values that replace the ?
    private String[] mSelectionArgs = { mSearchString };

    // An adapter that binds the result Cursor to the ListView
    private Cursor mCursor;

    RecyclerView mRecyclerView = null;
    List<Contact> mContactList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contact_list);

        // Initializes the loader
        getSupportLoaderManager().initLoader(0, null, this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*
        ContactsAdapter mAdapter = new ContactsAdapter(mCursor);

        final AppCompatActivity This = this;

        mAdapter.setOnItemClickListener(new ContactsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                    Intent intent = new Intent(This, ContactDetailsActivity.class);
                    // Pass data object in the bundle and populate details activity.
                    //intent.putExtra(ContactDetailsActivity.EXTRA_CONTACT, contact);
                    ImageView imageView = (ImageView) view.findViewById(R.id.CircleImageView);
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(This, imageView, "contact_picture");
                    startActivity(intent, options.toBundle());
            }
        });

        mRecyclerView.setAdapter(mAdapter);
        */
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
         /*
         * Makes search string into pattern and
         * stores it in the selection array
         */
        mSelectionArgs[0] = "%" + mSearchString + "%";
        // Starts the query
        return new CursorLoader(
                this,
                ContactsContract.Contacts.CONTENT_URI,
                PROJECTION,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Put the result Cursor in the adapter for the ListView

        ContactsAdapter mAdapter = new ContactsAdapter(data);

        final AppCompatActivity This = this;

        mAdapter.setOnItemClickListener(new ContactsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent = new Intent(This, ContactDetailsActivity.class);
                // Pass data object in the bundle and populate details activity.
                //intent.putExtra(ContactDetailsActivity.EXTRA_CONTACT, contact);
                ImageView imageView = (ImageView) view.findViewById(R.id.CircleImageView);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(This, imageView, "contact_picture");
                startActivity(intent, options.toBundle());
            }
        });

        mRecyclerView.setAdapter(mAdapter);
        //mCursor = data;
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Delete the reference to the existing Cursor
        mCursor = null;
    }
}
