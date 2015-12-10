package net.henriqueof.contactlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by henri on 06/12/2015.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    List<Contact> ContactList;
    OnItemClickListener ItemClickListener = null;

    public ContactsAdapter(List<Contact> ContactList) {
        this.ContactList = ContactList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_contact, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.ContactName.setText(ContactList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return ContactList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView ContactName;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ItemClickListener != null)
                        ItemClickListener.OnItemClick(v, getPosition());
                }
            });

            ContactName = (TextView) itemView.findViewById(R.id.contact_name);
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        ItemClickListener = itemClickListener;
    }
}
