import numpy as np
import scipy.io
import matplotlib.pyplot as plt
import random
import math

# a = np.matrix('1 2:3 4')
# total_loss = 0.0
# hyp = np.dot(theta.T, x_trn, t_loss)


def loss(trn_x, trn_y, theta_f):
    inner = np.square(np.dot(trn_x, theta_f) - trn_y)
    err = np.sum(inner)/(trn_x.shape[0])
    return err


def close_form(trn_x, trn_y):
    theta_f = np.dot(np.linalg.inv(np.dot(trn_x.T, trn_x)), np.dot(trn_x.T, trn_y))
    # print(theta)
    return theta_f


def stoch_grad_descent(trn_x, trn_y, l_rate, batch):
    theta_f = np.ones((trn_x.shape[1], 1))*5
    # idx = np.random.randint(120, size=batch)
    # idx = np.sort(idx)
    # batch_x = trn_x[idx, :]
    # print(theta_f)
    # batch_y = trn_y[idx, :]
    # print(batch_y)
    # gradient = (np.dot(batch_x.T, (np.dot(batch_x, theta_f) - batch_y)))*l_rate
    # current_loss = loss(batch_x, batch_y, theta_f)
    for it in range(5000):
        index_set = set()
        for i in range(trn_x.shape[0]):
            index_set.add(i)
        while len(index_set) > 0:
            idx = []
            if len(index_set) < batch:
                while len(index_set) > 0:
                    idx.append(index_set.pop())
            else:
                idx = random.sample(index_set, batch)
            for x_val in idx:
                # print(x_val)
                index_set.remove(x_val)
            # print(idx)
            batch_x = trn_x[idx, :]
            batch_y = trn_y[idx, :]
            gradient = (np.dot(batch_x.T, (np.dot(batch_x, theta_f) - batch_y))) * l_rate / batch
            theta_f = theta_f - gradient
        if it % 1000 == 0:
            # print(batch_x[:, 1], batch_y)
            print(loss(trn_x, trn_y, theta_f))
    return theta_f


def make_inputs(x_f, n):
    y_f = np.ones(x_f.shape[0])
    # print(x_f.shape[0])
    y_f = np.matrix(y_f)
    y_f = y_f.T
    x_ones = np.ones(x_f.shape[0])
    x_ones = np.matrix(x_ones)
    x_ones = x_ones.T
    x_sq = np.square(x_f)
    # print(x_sq.shape)
    x_f = np.append(x_ones, x_f, 1)
    x_f = np.append(x_f, x_sq, 1)
    n = n-2
    for i in range(n):
        for j in range(x_f.shape[0]):
            y_f[j, 0] = x_f[j, 1]*x_f[j, 2+i]
        x_f = np.append(x_f, y_f, 1)
    return x_f


data = scipy.io.loadmat('dataset1.mat')
x = np.asmatrix(data['X_trn'])
y = np.asmatrix(data['Y_trn'])
x_test = np.asmatrix(data['X_tst'])
y_test = np.asmatrix(data['Y_tst'])
ord = np.argsort(x, axis=0)
x = np.array(x)[ord]
x = np.asmatrix(x)
x = x.T
y = np.array(y)[ord]
y = np.asmatrix(y)
y = y.T

n = 3
learning_rate = 0.0001
batch_size = 10
x = make_inputs(x, n)

theta = stoch_grad_descent(x, y, learning_rate, batch_size)
theta_close = close_form(x, y)
ord = np.argsort(x_test, axis=0)
x_test = np.array(x_test)[ord]
x_test = np.asmatrix(x_test)
x_test = x_test.T
y_test = np.array(y_test)[ord]
y_test = np.asmatrix(y_test)
y_test = y_test.T
x_test = make_inputs(x_test, n)
print('theta ', theta, ' theta_close ', theta_close)
print('train', loss(x, y, theta))
print('train close', loss(x, y, theta_close))
print('test', loss(x_test, y_test, theta))
print('test close', loss(x_test, y_test, theta_close))

print(theta)
predict = np.matmul(x, theta)
plt.scatter([x[:, 1]], [y[:, 0]])
plt.plot(x[:, 1], predict[:, 0], color='red')
plt.show()

predict = np.matmul(x_test, theta)
plt.scatter([x_test[:, 1]], [y_test[:, 0]])
plt.plot(x_test[:, 1], predict[:, 0], color='red')
plt.show()


# print(data['X_trn'])
# print(data['X_trn'].ndim)
# print(data['Y_trn'])
# print(data['Y_trn'].ndim)
#
# combined = np.vstack((data['X_trn'].ndim, data['Y_trn'].ndim))
# print('shape of the data is [%d, %d]' % combined.shape)







