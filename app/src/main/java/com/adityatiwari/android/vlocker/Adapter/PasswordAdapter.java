package com.adityatiwari.android.vlocker.Adapter;

import android.content.Intent;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.adityatiwari.android.vlocker.Activity.UpdatePasswordActivity;
import com.adityatiwari.android.vlocker.Entity.Password;
import com.adityatiwari.android.vlocker.R;
import com.adityatiwari.android.vlocker.Tabs.PasswordFragment;

import java.util.List;

public class PasswordAdapter extends RecyclerView.Adapter<PasswordAdapter.PasswordViewHolder> {

    PasswordFragment passwordFragment;
    List<Password> passwords;

    public PasswordAdapter(PasswordFragment passwordFragment,List<Password> passwords){
            this.passwordFragment = passwordFragment;
            this.passwords = passwords;
    }

    @Override
    public PasswordAdapter.PasswordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PasswordViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_password,parent,false));
    }

    @Override
    public void onBindViewHolder(PasswordAdapter.PasswordViewHolder holder, int position) {
            Password password = passwords.get(position);
            holder.passwordToggleChar.setText(String.valueOf(password.website.charAt(0)));
            holder.username.setText(password.username);
            holder.website.setText(password.website);

            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(passwordFragment.getActivity(), UpdatePasswordActivity.class);
                intent.putExtra("id",password.id);
                intent.putExtra("username",password.username);
                intent.putExtra("password",password.password);
                intent.putExtra("website",password.website);
                passwordFragment.startActivity(intent);
            });
    }

    @Override
    public int getItemCount() {
        return passwords.size();
    }

    public static class PasswordViewHolder extends RecyclerView.ViewHolder{

        TextView passwordToggleChar, username,website;
        public PasswordViewHolder( View itemView) {
            super(itemView);
            passwordToggleChar = itemView.findViewById(R.id.password_toggle_char);
            username = itemView.findViewById(R.id.userName);
            website = itemView.findViewById(R.id.website);
        }
    }
}
