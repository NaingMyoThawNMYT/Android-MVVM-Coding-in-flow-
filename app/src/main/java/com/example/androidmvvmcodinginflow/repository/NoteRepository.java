package com.example.androidmvvmcodinginflow.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.androidmvvmcodinginflow.database.NoteDao;
import com.example.androidmvvmcodinginflow.database.NoteDatabase;
import com.example.androidmvvmcodinginflow.model.NoteModel;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<NoteModel>> notes;

    public NoteRepository(Context context) {
        NoteDatabase database = NoteDatabase.getInstance(context);
        noteDao = database.noteDao();
        notes = noteDao.getAll();
    }

    public void insert(NoteModel note) {
        new InsertAsyncTask(noteDao).execute(note);
    }

    public void update(NoteModel note) {
        new UpdateAsyncTask(noteDao).execute(note);

    }

    public void delete(NoteModel note) {
        new DeleteAsyncTask(noteDao).execute(note);

    }

    public void deleteAll() {
        new DeleteAllAsyncTask(noteDao).execute();
    }

    public LiveData<List<NoteModel>> getAll() {
        return notes;
    }

    private static class InsertAsyncTask extends AsyncTask<NoteModel, Void, Void> {
        private NoteDao noteDao;

        public InsertAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteModel... noteModels) {
            noteDao.insert(noteModels[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<NoteModel, Void, Void> {
        private NoteDao noteDao;

        public UpdateAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteModel... noteModels) {
            noteDao.update(noteModels[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<NoteModel, Void, Void> {
        private NoteDao noteDao;

        public DeleteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteModel... noteModels) {
            noteDao.delete(noteModels[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        public DeleteAllAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAll();
            return null;
        }
    }
}
