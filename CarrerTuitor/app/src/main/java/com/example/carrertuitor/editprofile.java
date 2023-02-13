
package com.example, log insignuprealtime:

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget. EditText;

import android.widget.Toast;

import com.google.firebase.database.DatabaseReference: import com.google.firebase.database.FirebaseDatabase:

public class EditProfileActivity extends AppCompatActivity {

EditText editName, editEmail, editUsername, editPassword;

Button saveButton;

String nameUser, emailUser, usernameUser, passwordUser;

DatabaseReference reference;

@Override

protected void onCreate(Bundle savedInstanceState) I

super.onCreate(savedInstanceState);

setContentView(R.layout.activity_edit_profile);

reference = FirebaseDatabase.getInstance().getReference("users");

editName = findViewById(R.id.editName);

editEmail = findViewById(R.id.editEmail);

editUsername = findViewById(R.id.editUsername);

editPassword = findViewById(R.id.edit Password):

saveButton = findViewById(R.id.saveButton);

showData();

saveButton.setOnClickListener(new View.OnClickListener() [

@Override

public void onClick(View view) {

if (18NameChanged() || 18PasswordChanged() 11 isEmailChanged()) {

Toast.makeText(EditProfileActivity.this, "Saved", Toast.LENGTH_SHORT).show();
  saveButton.setOnClickListener(new View.OnClickListener() {

@Override

public void onClick(View view) {

if (isNameChanged() || isPasswordChanged() || ismailChanged()) { Toast.makeText(EditProfileActivity.this. "Saved", Toast.LENGTH_SHORT).show();

} else {

Toast.makeText(Edit ProfileActivity.this, "No Changes Found", Toast.LENGTH_SHORT).sho

31:

private boolean isNameChanged() {

if (!nameUser.equals(editName.getText().toString(111t reference.child(usernameUser).child("name").setValue(editName.getText().toString()); nameUser = editName.getText().toString();

return true;

} else {

return false;

private boolean isEmailChanged() {

if (!emailUser.equals(editEmail.getText().toString())){

reference.child(usernameUser).child('email').setValue(editEmail.getText().toString()); emailUser = editEmail.getText().toString();

return true;

} else {

return false;

private boolean isPasswordChanged() {

if (passwordUser.equals(editPassword.getText().toString())) {

reference.child(usernameUser).child("password").setValue(editPassword.getText().toString()); passwordUser = edit Password. getText().toString();

return true;

} else {

ㅈ

return false;
  private boolean isPasswordChanged() {

if (passwordUser.equals(editPassword.getText().toString())){ reference.child(usernameUser).child('password').setValue(edit Password.getText().toString()); passwordUser = editPassword.getText().toString();

return true;

} else {

return false;

public void showData() {

Intent intent = getIntent();

nameUser = intent.getStringExtra("name");

emailUser = intent.getStringExtra("email");

username User = intent.getStringExtra("username"

passwordUser = intent.getStringExtra("password");

editName.setText(nameUser);

editEmail.setText(emailUser);

editUsername.setText(username User);

editPassword.setText(passwordUser);

}
