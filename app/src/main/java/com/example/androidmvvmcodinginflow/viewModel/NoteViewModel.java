package com.example.androidmvvmcodinginflow.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.androidmvvmcodinginflow.model.NoteModel;
import com.example.androidmvvmcodinginflow.repository.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<NoteModel>> notes;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        repository = new NoteRepository(application);
        notes = repository.getAll();
    }

    public void insert(NoteModel note) {
        repository.insert(note);
    }

    public void update(NoteModel note) {
        repository.update(note);
    }

    public void delete(NoteModel note) {
        repository.delete(note);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public LiveData<List<NoteModel>> getAll() {
        return notes;
    }
}
