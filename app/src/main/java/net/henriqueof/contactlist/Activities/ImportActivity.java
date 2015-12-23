package net.henriqueof.contactlist.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import net.henriqueof.contactlist.Adapters.ContactsAdapter;
import net.henriqueof.contactlist.Adapters.ContactsImportAdapter;
import net.henriqueof.contactlist.Helpers.DividerItemDecoration;
import net.henriqueof.contactlist.R;

public class ImportActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    /*
     * Defines an array that contains column names to move from
     * the Cursor to the ListView.
     */
    private final static String[] PROJECTION = {
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.LOOKUP_KEY,
    };

    RecyclerView mRecyclerView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);

        // ...
        mRecyclerView = (RecyclerView)findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(new ContactsImportAdapter());

        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        /*
         * Makes search string into pattern and
         * stores it in the selection array
         */
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
                // TODO: Pass data object in the bundle and populate details activity.
                //intent.putExtra(ContactDetailsActivity.EXTRA_CONTACT, contact);
                ImageView imageView = (ImageView) view.findViewById(R.id.CircleImageView);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(This, imageView, "contact_picture");
                startActivity(intent, options.toBundle());
            }
        });

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }
}
