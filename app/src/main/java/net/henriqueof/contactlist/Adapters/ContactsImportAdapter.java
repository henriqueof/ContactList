package net.henriqueof.contactlist.Adapters;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.henriqueof.contactlist.Models.Contact;
import net.henriqueof.contactlist.R;

/**
 * Created by Carlos Henrique on 21/12/2015.
 */
public class ContactsImportAdapter extends RecyclerView.Adapter<ContactsImportAdapter.ViewHolder> {
    Cursor mCursor = null;
    int mNameColumnIndex, mIdColumnIndex;

    ContactsAdapter.OnItemClickListener ItemClickListener = null;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_contact_import, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Extract info from cursor
        mCursor.moveToPosition(position);
        String contactName = mCursor.getString(mNameColumnIndex);
        long contactId = mCursor.getLong(mIdColumnIndex);

        // Create contact model and bind to ViewHolder
        Contact contact = new Contact(contactName);
        holder.contactName.setText(contact.getName());
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView contactName;

        public ViewHolder(View itemView) {
            super(itemView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ItemClickListener != null)
                        ItemClickListener.OnItemClick(v, getPosition());
                }
            });

            contactName = (TextView) itemView.findViewById(R.id.contact_name);
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }
}
