package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int storageSize;

    public void clear() {
        Arrays.fill(storage, 0, storageSize, null);
        storageSize = 0;
    }

    public void save(Resume resume) {
        if (storageSize >= storage.length) {
            System.out.println("Хранилище резюме переполнено");
            return;
        }
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            storage[storageSize] = resume;
            storageSize++;
        } else {
            System.out.println("Резюме " + resume.getUuid() + " не сохранено, так как уже имеется в хранилище");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("В хранилище нет резюме с " + uuid);
            return null;
        }
        return storage[index];
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("В хранилище нет резюме с " + resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("В хранилище нет резюме с " + uuid);
            return;
        }
        storage[index] = null;
        if (storageSize - 1 - index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, storageSize - 1 - index);
        }
        storageSize--;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, storageSize);
    }

    public int size() {
        return storageSize;
    }
}
