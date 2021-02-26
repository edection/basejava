import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int storageSize;

    void clear() {
        Arrays.fill(storage, 0, storageSize, null);
        storageSize = 0;
    }

    void save(Resume resume) {
        if (storageSize >= 10000) {
            System.out.println("Переполнено");
            return;
        }
        int index = index(resume.getUuid());
        if (index == -1) {
            storage[storageSize] = resume;
            storageSize++;
        } else {
            System.out.println("Резюме есть");
            return;
        }
    }

    Resume get(String uuid) {
        int index = index(uuid);
        if (index == -1) {
            System.out.println("Резюме нет");
            return null;
        } else {
            return storage[index];
        }
    }

    void update(Resume resume) {
        int index = index(resume.getUuid());
        if (index == -1) {
            System.out.println("Резюме нет");
        } else {
            storage[index] = resume;
        }
    }

    void delete(String uuid) {
        int index = index(uuid);
        if (index == -1) {
            System.out.println("Резюме нет");
            return;
        } else {
            storage[index] = null;
            for (int j = index; j < storageSize - 1; j++) {
                storage[j] = storage[j + 1];
            }
            storageSize--;
            storage[storageSize] = null;
            return;
        }
    }

    int index(String uuid) {
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
    Resume[] getAll() {
        return Arrays.copyOf(storage, storageSize);
    }

    int size() {
        return storageSize;
    }
}
