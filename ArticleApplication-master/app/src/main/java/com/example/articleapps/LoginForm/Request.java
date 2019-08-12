package com.example.articleapps.LoginForm;

import android.content.Intent;
import android.os.AsyncTask;

import com.example.articleapps.MainMenu.MainMenu;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import static com.example.articleapps.LoginForm.Activity_Register.EmailInput;
import static com.example.articleapps.LoginForm.Activity_Register.etEmail;
import static com.example.articleapps.LoginForm.Activity_Register.etName;
import static com.example.articleapps.LoginForm.Activity_Register.etPassWord;

