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
            System.out.println("Переполнено");
            return;
        }
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            storage[storageSize] = resume;
            storageSize++;
        } else {
            System.out.println("Есть резюме с " + resume.getUuid());
            return;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Нет резюме с " + uuid);
            return null;
        }
        return storage[index];
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("Нет резюме с " + resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Нет резюме с " + uuid);
            return;
        }
        storage[index] = null;
        for (int j = index; j < storageSize - 1; j++) {
            storage[j] = storage[j + 1];
        }
        storageSize--;
        storage[storageSize] = null;
        return;

    }

    int getIndex(String uuid) {
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
