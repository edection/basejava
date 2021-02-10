import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int storageSize;

    void clear() {
        for (int i = 0; i < storageSize; i++) {
            storage[i] = null;
        }
        storageSize = 0;
    }

    void save(Resume resume) {
        storage[storageSize] = resume;
        storageSize++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = null;
                for (int j = i; j < storageSize - 1; j++) {
                    storage[j] = storage[j + 1];
                }
                storageSize--;
                storage[storageSize] = null;
                break;
            }
        }
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
