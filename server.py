from flask import Flask, send_file

app = Flask(__name__)

@app.route('/')
def serve_post_html():
    # Return the 'post.html' file located in the same directory as this script
    return send_file('post.html')

@app.route('/update')
def update():
    try:
        with open('tmp_writer.html', 'r') as file:
            content = file.read()
        return content
    except FileNotFoundError:
        return "File not found", 404
    except Exception as e:
        print("Error reading file:", e)
        return "Error reading file", 500

if __name__ == '__main__':
    port = 6200
    print(port)
    app.run(port=port)
