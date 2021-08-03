package client.model.tasks;

import email.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

public class Online implements Callable<Boolean> {

    @Override
    public Boolean call() {
        Socket socket = null;
        try {
            socket = new Socket("192.168.43.1", 6789);
            if (!socket.isClosed()) {
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                Message request = new Message();
                request.setComando("ONLINE");
                out.writeObject(request);
                out.flush();
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
