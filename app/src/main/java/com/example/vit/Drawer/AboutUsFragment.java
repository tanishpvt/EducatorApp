package com.example.vit.Drawer;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.vit.R;

public class AboutUsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contributors, container, false);

        LinearLayout C1 = view.findViewById(R.id.contributor_1);
        LinearLayout C2 = view.findViewById(R.id.contributor_2);
        LinearLayout C3 = view.findViewById(R.id.contributor_3);
        LinearLayout C4 = view.findViewById(R.id.contributor_4);

        ContributorDetailFragment fragment = new ContributorDetailFragment();
        Bundle bundle = new Bundle();
        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();

        C1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView T1 = view.findViewById(R.id.contributor1_name);
                String t1 = (String) T1.getText();

                Uri uri = Uri.parse("android.resource://" + requireActivity().getPackageName() + "/" + R.drawable.tanish);

                fragment.setArguments(bundle);
                bundle.putString("name1",t1);
                bundle.putString("image", uri.toString());
                bundle.putString("github","Tanishpvt");
                bundle.putString("linkedin","TanishPvt");
                bundle.putString("email","Tanish@gmail.com");
                bundle.putString("website","https://tanishpvt.github.io/Developerss-Studio/");
                fragmentTransaction.addToBackStack(this.getClass().getName());
                fragmentTransaction.setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out);
                fragmentTransaction.replace(R.id.container,fragment);
                fragmentTransaction.commit();
            }
        });

        C2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView T1 = view.findViewById(R.id.contributor2_name);
                String t1 = (String) T1.getText();

                Uri uri = Uri.parse("android.resource://" + requireActivity().getPackageName() + "/" + R.drawable.sameep);

                fragment.setArguments(bundle);
                bundle.putString("name1",t1);
                bundle.putString("github","Sameep");
                bundle.putString("image", uri.toString());
                bundle.putString("linkedin","sameepSawant");
                bundle.putString("email","sameep@gmail.com");
                bundle.putString("website","https://tanishpvt.github.io/Developerss-Studio/");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out
                );
                fragmentTransaction.replace(R.id.container,fragment);
                fragmentTransaction.commit();
            }
        });

        C3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView T1 = view.findViewById(R.id.contributor3_name);
                String t1 = (String) T1.getText();

                Uri uri = Uri.parse("android.resource://" + requireActivity().getPackageName() + "/" + R.drawable.leon);

                fragment.setArguments(bundle);
                bundle.putString("name1",t1);
                bundle.putString("image", uri.toString());
                bundle.putString("github","Leon");
                bundle.putString("linkedin","leon@gmail.com");
                bundle.putString("email","leon@gmail.com");
                bundle.putString("website","https://tanishpvt.github.io/Developerss-Studio/");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out
                );
                fragmentTransaction.replace(R.id.container,fragment);
                fragmentTransaction.commit();
            }
        });

        C3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView T1 = view.findViewById(R.id.contributor4_name);
                String t1 = (String) T1.getText();

                Uri uri = Uri.parse("android.resource://" + requireActivity().getPackageName() + "/" + R.drawable.sagar);

                fragment.setArguments(bundle);
                bundle.putString("name1",t1);
                bundle.putString("image", uri.toString());
                bundle.putString("github","Sagar");
                bundle.putString("linkedin","sagar@gmail.com");
                bundle.putString("email","sagar@gmail.com");
                bundle.putString("website","https://tanishpvt.github.io/Developerss-Studio/");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out
                );
                fragmentTransaction.replace(R.id.container,fragment);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}
