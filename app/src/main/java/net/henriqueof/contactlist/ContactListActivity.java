package net.henriqueof.contactlist;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {
    RecyclerView mRecyclerView = null;
    List<Contact> mContactList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contact_list);

        mContactList = new ArrayList<>();

        for (int i = 0; i < 100; i++)
            mContactList.add(new Contact("Contato Nº " + (i + 1)));

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ContactsAdapter mAdapter = new ContactsAdapter(mContactList);

        final AppCompatActivity This = this;

        mAdapter.setOnItemClickListener(new ContactsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                    Intent intent = new Intent(This, ContactDetailsActivity.class);
                    // Pass data object in the bundle and populate details activity.
                    //intent.putExtra(ContactDetailsActivity.EXTRA_CONTACT, contact);
                    ImageView imageView = (ImageView) view.findViewById(R.id.CircleImageView);
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(This, (View) imageView, "contact_picture");
                    startActivity(intent, options.toBundle());
            }
        });

        mRecyclerView.setAdapter(mAdapter);
    }
}
