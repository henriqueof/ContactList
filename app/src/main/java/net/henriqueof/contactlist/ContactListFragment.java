package net.henriqueof.contactlist;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ContactListFragment extends Fragment {

    RecyclerView mRecyclerView = null;
    List<Contact> mContactList = null;

    public ContactListFragment() {
        mContactList = new ArrayList<>();

        for (int i = 0; i < 100; i++)
            mContactList.add(new Contact("Contato " + (i + 1)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ContactsAdapter mAdapter = new ContactsAdapter(mContactList);
        mAdapter.setOnItemClickListener(new ContactsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                boolean IsFragment = true;
                if (IsFragment) {
                    //setSharedElementReturnTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.change_image_transform));
                    //setExitTransition(TransitionInflater.from(getActivity()).inflateTransition(android.R.transition.explode));

                    // Create new fragment to add (Fragment B)
                    Fragment fragment = new ContactDetailsFragment();
                    //fragment.setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.change_image_transform));
                    //fragment.setEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(android.R.transition.explode));

                    // Our shared element (in Fragment A)
                    ImageView mContactImage = (ImageView) view.findViewById(R.id.CircleImageView);

                    // Add Fragment B
                    FragmentTransaction ft = getFragmentManager().beginTransaction()
                            .replace(R.id.container, fragment)
                            .addToBackStack("transaction")
                            .addSharedElement(mContactImage, "contact_picture");
                    ft.commit();
                }
                else
                {
                    Intent intent = new Intent(getActivity(), ContactDetailsActivity.class);
                    // Pass data object in the bundle and populate details activity.
                    //intent.putExtra(ContactDetailsActivity.EXTRA_CONTACT, contact);
                    ImageView imageView = (ImageView) view.findViewById(R.id.CircleImageView);
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), imageView, "contact_picture");
                    getActivity().startActivity(intent, options.toBundle());
                }
            }
        });

        mRecyclerView.setAdapter(mAdapter);
    }
}
